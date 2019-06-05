package com.aurimteam.justhobby.user.company_info.course_info

import com.aurimteam.justhobby.response.CourseResponse

interface ICompanyInfoView {
    fun showCompanyCourses(courses: List<CourseResponse>)
}