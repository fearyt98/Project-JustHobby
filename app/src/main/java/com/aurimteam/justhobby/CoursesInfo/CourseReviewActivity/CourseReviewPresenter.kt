package com.aurimteam.justhobby.CoursesInfo.CourseReviewActivity

class CourseReviewPresenter(private var view: ICourseReviewView?, private val model: ICourseReviewModel?) :
    CourseReviewModel.onFinishedListener {

    override fun onResultSuccess() {
        view?.showReview()
    }

    override fun onResultFail() {

    }

    fun getCourseReview() {
        model?.getCourseReviewData()
    }

    fun onDestroy() {
        view = null
    }
}