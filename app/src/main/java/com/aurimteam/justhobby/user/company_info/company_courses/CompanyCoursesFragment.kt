package com.aurimteam.justhobby.user.company_info.company_courses


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.course.CourseAdapter
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import com.aurimteam.justhobby.user.main.main_nav.MainNavActivity
import kotlinx.android.synthetic.main.fragment_company_courses.*

class CompanyCoursesFragment : Fragment(), ICompanyCoursesView {

    private val presenter = CompanyCoursesPresenter(this, CompanyCoursesModel())
    private val adapter = CourseAdapter(presenter)
    private var companyId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_company_courses, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (arguments != null) {
            companyId = arguments!!.get("company_id")!!.toString().toLong()
            companyCoursesTitle.text = arguments!!.get("company_name")!!.toString()

            if (context != null && activity != null) {
                val gpsData = (activity as MainNavActivity).gpsData
                presenter.getCompanyCourses(context!!, companyId, gpsData.returnLat(), gpsData.returnLon())
            }
        }
        companyCoursesBtnBack.setOnClickListener { back() }
        companyCoursesRecyclerView.layoutManager = LinearLayoutManager(context)
        companyCoursesRecyclerView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showCompanyCourses(courses: List<CourseResponseR>, included: IncludedResponse?) {
        if (courses.isEmpty() || included == null) {
            companyCoursesProgressBar.visibility = View.GONE
            companyCoursesRecyclerView.visibility = View.GONE
        } else {
            toggleContentPB(false)
            adapter.onDataChange(courses, included)
        }
    }

    override fun deletedUserBookmark(position: Int) {
        adapter.deletedBookmark(position)
    }

    override fun addedUserBookmark(position: Int) {
        adapter.addedBookmark(position)
    }

    override fun toggleContentPB(isVisiblePB: Boolean) {
        if (isVisiblePB) {
            companyCoursesProgressBar.visibility = View.VISIBLE
            companyCoursesRecyclerView.visibility = View.GONE
        } else {
            companyCoursesProgressBar.visibility = View.GONE
            companyCoursesRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?) {
        val devMode = Settings(context!!).getPropertyBoolean("dev_mode", false)
        if (devMode != null && devMode) {
            val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 30)
            toast.show()
        }
        toggleContentPB(false)
    }

    private fun back() {
        fragmentManager?.popBackStack()
    }
}
