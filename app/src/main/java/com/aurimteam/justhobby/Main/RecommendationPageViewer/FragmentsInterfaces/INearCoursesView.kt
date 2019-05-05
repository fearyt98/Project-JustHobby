package com.aurimteam.justhobby.Main.RecommendationPageViewer.FragmentsInterfaces

import com.aurimteam.justhobby.Response.CourseResponse

interface INearCoursesView {
    fun showNearUserCourses(courses: List<CourseResponse>)
}