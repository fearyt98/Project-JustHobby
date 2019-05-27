package com.aurimteam.justhobby.CoursesInfo.CourseReviewActivity

class CourseReviewModel : ICourseReviewModel{
    interface onFinishedListener{
        fun onResultSuccess()
        fun onResultFail()
    }

    override fun getCourseReviewData() {

    }

}