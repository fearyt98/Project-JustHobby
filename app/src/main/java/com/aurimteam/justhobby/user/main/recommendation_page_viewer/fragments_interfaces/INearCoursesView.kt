package com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces

import com.aurimteam.justhobby.response.CourseResponse

interface INearCoursesView {
    fun showNearUserCourses(courses: List<CourseResponse>)
}