package com.aurimteam.justhobby.user.course_info.course_review_new

class CourseReviewNewPresenter(private var view: ICourseReviewNewView?, private val model: ICourseReviewNewModel?) :
    CourseReviewNewModel.OnFinishedListener {
    override fun onResultSuccess() {

    }

    override fun onResultFail() {

    }

    fun sendNewReview(){
        model?.sendNewReviewData()
    }

    fun onDestroy() {
        view = null
    }
}