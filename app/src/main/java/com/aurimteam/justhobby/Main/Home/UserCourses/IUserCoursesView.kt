package com.aurimteam.justhobby.Main.Home.UserCourses

import com.aurimteam.justhobby.Response.UserCourseResponse

interface IUserCoursesView {
    fun showUserCourses(userCourses: List<UserCourseResponse>)
}