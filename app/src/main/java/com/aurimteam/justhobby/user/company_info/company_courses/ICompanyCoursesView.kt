package com.aurimteam.justhobby.user.company_info.company_courses

import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

interface ICompanyCoursesView {
    fun showCompanyCourses(courses: List<CourseResponseR>, included: IncludedResponse?)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun deletedUserBookmark(position: Int)
    fun addedUserBookmark(position: Int)
}