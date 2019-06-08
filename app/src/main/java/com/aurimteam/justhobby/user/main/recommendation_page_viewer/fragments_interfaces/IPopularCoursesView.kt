package com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces

import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

interface IPopularCoursesView {
    fun showPopularCourses(courses: List<CourseResponseR>, included: IncludedResponse?)
}