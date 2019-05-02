package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces

import com.aurimteam.justhobby.Response.CourseResponse

interface IPopularCoursesView {
    fun showPopularCourses(courses: List<CourseResponse>)
}