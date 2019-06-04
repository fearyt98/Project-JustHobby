package com.aurimteam.justhobby.user.main.home.user_courses

import com.aurimteam.justhobby.response.UserCourseResponse

interface IUserCoursesView {
    fun showUserCourses(userCourses: List<UserCourseResponse>)
}