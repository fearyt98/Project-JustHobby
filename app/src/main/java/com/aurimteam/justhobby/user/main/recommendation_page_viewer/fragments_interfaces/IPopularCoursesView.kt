package com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces

import com.aurimteam.justhobby.response.CourseResponse

interface IPopularCoursesView {
    fun showPopularCourses(courses: List<CourseResponse>)
}