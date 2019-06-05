package com.aurimteam.justhobby.user.course_info.course_reviews

import com.aurimteam.justhobby.response.CourseReviewsResponse

interface ICourseReviewsView {
    fun showCourseReviews(courseReviews: List<CourseReviewsResponse>)
}