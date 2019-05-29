package com.aurimteam.justhobby.Main.Home.UserCourses

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.UserCourseResponse
import kotlinx.android.synthetic.main.fragment_user_courses.*

class UserCoursesFragment : Fragment(), IUserCoursesView {

    private val presenter = UserCoursesPresenter(this, UserCoursesModel())
    private val adapter = UserCoursesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_courses, container, false)
        //view.findViewById<ImageButton>(R.id.homeBookmarks).setOnClickListener { openUserBookmarks() }
        return view
    }

    override fun showUserCourses(userCourses: List<UserCourseResponse>) {
        adapter.onDataChange(userCourses)
    }
    override fun onStart() {
        super.onStart()
        presenter.getUserCourses()
        userCoursesRecyclerView.layoutManager = LinearLayoutManager(context)
        userCoursesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
