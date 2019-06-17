package com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Space
import android.widget.Toast
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.IPopularCoursesView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.course.CourseAdapter
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import com.aurimteam.justhobby.user.main.main_nav.MainNavActivity
import kotlinx.android.synthetic.main.fragment_popular_courses.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

class PopularCoursesFragment : Fragment(), IPopularCoursesView {

    private val presenter = PopularCoursesPresenter(this, PopularCoursesModel())
    private var adapter = CourseAdapter(presenter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_courses, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (context != null && activity != null) {
            val gpsData = (activity as MainNavActivity).gpsData
            presenter.getPopularCourses(context!!, gpsData.returnLat(), gpsData.returnLon())
        }
        
        popularCoursesRecyclerView.layoutManager = LinearLayoutManager(context)
        popularCoursesRecyclerView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.findViewById<BottomNavigationView>(R.id.mainNavNavigation)?.visibility = View.VISIBLE
        activity?.findViewById<Space>(R.id.mainNavMarginSpacer)?.visibility = View.VISIBLE
        presenter.detachView()
    }

    override fun showPopularCourses(courses: List<CourseResponseR>, included: IncludedResponse?) {
        if (courses.isEmpty() || included == null) {
            popularCoursesProgressBar.visibility = View.GONE
            popularCoursesRecyclerView.visibility = View.GONE
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
            popularCoursesProgressBar.visibility = View.VISIBLE
            popularCoursesRecyclerView.visibility = View.GONE
        } else {
            popularCoursesProgressBar.visibility = View.GONE
            popularCoursesRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
        toggleContentPB(false)
    }
}
