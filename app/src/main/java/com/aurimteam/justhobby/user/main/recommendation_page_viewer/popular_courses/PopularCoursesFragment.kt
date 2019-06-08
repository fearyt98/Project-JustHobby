package com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.IPopularCoursesView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import kotlinx.android.synthetic.main.fragment_popular_courses.*

class PopularCoursesFragment : Fragment(), IPopularCoursesView {

    private val presenter = PopularCoursesPresenter(this, PopularCoursesModel())
    private var adapter = PopularCoursesAdapter(presenter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_courses, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (context != null) presenter.getPopularCourses(context!!)
        popularCoursesRecyclerView.layoutManager = LinearLayoutManager(context)
        popularCoursesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
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

    override fun deletedUserBookmarks(position: Int) {
        adapter.deletedBookmark(position)
    }

    override fun addedUserBookmarks(position: Int) {
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
    }
}
