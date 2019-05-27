package com.aurimteam.justhobby.CoursesInfo.CourseReviewNewActivity

class CourseReviewNewPresenter(private var view: ICourseReviewNewView?, private val model: ICourseReviewNewModel?) :
    CourseReviewNewModel.onFinishedListener {
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