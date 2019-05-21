package com.aurimteam.justhobby.CompanyInfo.CompanyInfoMainActivity

import com.aurimteam.justhobby.Response.CourseResponse

interface ICompanyInfoView {
    fun showCompanyCourses(courses: List<CourseResponse>)
}