package com.aurimteam.justhobby.user.course_info.course_review_new

class CourseReviewNewModel : ICourseReviewNewModel {
    interface OnFinishedListener {
        fun onResultSuccess()
        fun onResultFail(strError: String?)
    }

    override fun sendNewReviewData() {

    }

}