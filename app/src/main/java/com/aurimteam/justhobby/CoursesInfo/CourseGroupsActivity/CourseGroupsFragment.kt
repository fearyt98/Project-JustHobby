package com.aurimteam.justhobby.CoursesInfo.CourseGroupsActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R

class CourseGroupsFragment : Fragment(), ICourseGroupsView {

    private val presenter = CourseGroupsPresenter(this, CourseGroupsModel())
    private val adapter = CourseGroupsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
