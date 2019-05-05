package com.aurimteam.justhobby.Main.RecommendationPageViewer.FragmentsInterfaces

import com.aurimteam.justhobby.Response.CourseResponse

interface IPopularCoursesView {
    fun showPopularCourses(courses: List<CourseResponse>)
}