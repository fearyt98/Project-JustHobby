package com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces

import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

interface INearCoursesView {
    fun showNearUserCourses(courses: List<CourseResponseR>, included: IncludedResponse?)
}