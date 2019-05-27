package com.aurimteam.justhobby.CoursesInfo.CourseReviewsActivity

import com.aurimteam.justhobby.Response.CourseReviewsResponse

interface ICourseReviewsView {
    fun showCourseReviews(courseReviews: List<CourseReviewsResponse>)
}