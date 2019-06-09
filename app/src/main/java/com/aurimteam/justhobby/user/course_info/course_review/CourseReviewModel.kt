package com.aurimteam.justhobby.user.course_info.course_review

class CourseReviewModel : ICourseReviewModel{
    interface OnFinishedListener{
        fun onResultSuccess()
        fun onResultFail(strError: String?)
    }

    override fun getCourseReviewData() {

    }

}