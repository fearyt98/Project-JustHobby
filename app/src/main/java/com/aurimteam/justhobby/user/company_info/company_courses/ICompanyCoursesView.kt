package com.aurimteam.justhobby.user.company_info.company_courses

import com.aurimteam.justhobby.response.CourseResponse

interface ICompanyCoursesView {
    fun showCompanyCourses(courses: List<CourseResponse>)
}