package com.aurimteam.justhobby.user.course_info.course_review_new

interface ICourseReviewNewModel {
    fun sendNewReviewData(courseId: Long, review: String, rating: Int, token: String, onFinishedListener: CourseReviewNewModel.OnFinishedListener)
}