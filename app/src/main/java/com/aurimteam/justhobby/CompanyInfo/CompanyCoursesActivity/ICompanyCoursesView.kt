package com.aurimteam.justhobby.CompanyInfo.CompanyCoursesActivity

import com.aurimteam.justhobby.Response.CourseResponse

interface ICompanyCoursesView {
    fun showCompanyCourses(courses: List<CourseResponse>)
}