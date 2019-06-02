package com.aurimteam.justhobby.CompanyInfo.CompanyInfoActivity

import com.aurimteam.justhobby.Response.CourseResponse

interface ICompanyInfoView {
    fun showCompanyCourses(courses: List<CourseResponse>)
}