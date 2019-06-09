package com.aurimteam.justhobby.user.course_info.course_reviews

import com.aurimteam.justhobby.response.ReviewResponse

class CourseReviewsModel : ICourseReviewsModel {
    interface OnFinishedListener {
        fun onResultSuccess(courseReviews: List<ReviewResponse>)
        fun onResultFail(strError: String?)
    }

    override fun getCourseReviewsData(onFinishedListener: OnFinishedListener) {

    }
}