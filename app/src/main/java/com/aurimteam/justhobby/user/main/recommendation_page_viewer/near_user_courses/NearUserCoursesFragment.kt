package com.aurimteam.justhobby.user.main.recommendation_page_viewer.near_user_courses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.INearCoursesView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.course.CourseAdapter
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import kotlinx.android.synthetic.main.fragment_near_user_courses.*

class NearUserCoursesFragment : Fragment(), INearCoursesView {

    private val presenter = NearUserCoursesPresenter(this, NearUserCoursesModel())
    private var adapter = CourseAdapter(presenter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_near_user_courses, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)
        if (context != null) presenter.getNearCourses(context!!)
        nearCoursesRecyclerView.layoutManager = LinearLayoutManager(context)
        nearCoursesRecyclerView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showNearUserCourses(courses: List<CourseResponseR>, included: IncludedResponse?) {
        if (courses.isEmpty() || included == null) {
            nearCoursesProgressBar.visibility = View.GONE
            nearCoursesRecyclerView.visibility = View.GONE
            nearCoursesClear.visibility = View.VISIBLE
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
            nearCoursesProgressBar.visibility = View.VISIBLE
            nearCoursesRecyclerView.visibility = View.GONE
        } else {
            nearCoursesProgressBar.visibility = View.GONE
            nearCoursesRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }
}
