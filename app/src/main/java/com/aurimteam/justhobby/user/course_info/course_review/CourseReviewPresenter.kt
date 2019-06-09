package com.aurimteam.justhobby.user.course_info.course_review

class CourseReviewPresenter(private var view: ICourseReviewView?, private val model: ICourseReviewModel?) :
    CourseReviewModel.OnFinishedListener {

    override fun onResultSuccess() {
        view?.showReview()
    }

    override fun onResultFail(strError: String?) {

    }

    fun getCourseReview() {
        model?.getCourseReviewData()
    }

    fun onDestroy() {
        view = null
    }
}