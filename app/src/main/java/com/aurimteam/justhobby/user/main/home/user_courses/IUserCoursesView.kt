package com.aurimteam.justhobby.user.main.home.user_courses

import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.response.CourseResponse

interface IUserCoursesView {
    fun showUserCourses(userCourses: List<CategoryResponse>, included: List<CourseResponse>)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean) {

    }
}