package com.aurimteam.justhobby.CoursesInfo.CourseGroupsActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.GroupResponse
import kotlinx.android.synthetic.main.fragment_course_groups.*

class CourseGroupsFragment : Fragment(), ICourseGroupsView {

    private val presenter = CourseGroupsPresenter(this, CourseGroupsModel())
    private val adapter = CourseGroupsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_course_groups, container, false)
        view.findViewById<ImageButton>(R.id.courseGroupsBtnBack).setOnClickListener { backToRecommendedFragment() }
        return view
    }

    override fun showCourseAllGroupse(courseAllGroups: List<GroupResponse>) {
        adapter.onDataChange(courseAllGroups)
    }
    override fun onStart() {
        super.onStart()
        presenter.getCourseAllGroups()
        courseGroupsRecyclerView.layoutManager = LinearLayoutManager(context)
        courseGroupsRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun backToRecommendedFragment() {
        fragmentManager?.popBackStack()
    }
}
