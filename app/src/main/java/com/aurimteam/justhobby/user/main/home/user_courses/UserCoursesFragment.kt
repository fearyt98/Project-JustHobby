package com.aurimteam.justhobby.user.main.home.user_courses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.UserCourseResponse
import kotlinx.android.synthetic.main.fragment_user_courses.*

class UserCoursesFragment : Fragment(), IUserCoursesView {

    private val presenter = UserCoursesPresenter(this, UserCoursesModel())
    private val adapter = UserCoursesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_courses, container, false)
        //view.findViewById<ImageButton>(R.id.homeBookmarks).setOnClickListener { openUserBookmarks() }
        return view
    }

    override fun showUserCourses(userCourses: List<CategoryResponse>, included: List<CourseResponse>) {
        adapter.onDataChange(userCourses, included)
    }

    override fun onStart() {
        super.onStart()
        if (context != null)
            presenter.getUserCourses(context!!)
        userCoursesRecyclerView.layoutManager = LinearLayoutManager(context)
        userCoursesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }
}
