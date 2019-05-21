package com.aurimteam.justhobby.CompanyInfo.CompanyCoursesInfoActivity

import com.aurimteam.justhobby.Response.CourseResponse

interface ICompanyCoursesView {
    fun showCompanyCourses(courses: List<CourseResponse>)
}