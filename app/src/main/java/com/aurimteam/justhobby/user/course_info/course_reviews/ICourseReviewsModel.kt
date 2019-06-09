package com.aurimteam.justhobby.user.course_info.course_reviews

interface ICourseReviewsModel {
    fun getCourseReviewsData(token: String, courseId: Long, onFinishedListener: CourseReviewsModel.OnFinishedListener)
}