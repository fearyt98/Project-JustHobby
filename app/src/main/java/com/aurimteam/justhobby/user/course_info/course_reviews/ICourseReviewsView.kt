package com.aurimteam.justhobby.user.course_info.course_reviews

import com.aurimteam.justhobby.response.ReviewResponse

interface ICourseReviewsView {
    fun showCourseReviews(courseReviews: List<ReviewResponse>)
}