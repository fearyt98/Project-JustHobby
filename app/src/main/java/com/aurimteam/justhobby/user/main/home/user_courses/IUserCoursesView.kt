package com.aurimteam.justhobby.user.main.home.user_courses

import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.GroupResponse
import com.aurimteam.justhobby.response.IncludedResponse

interface IUserCoursesView {
    fun showUserCourses(userCourses: List<GroupResponse>, included: IncludedResponse)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
}