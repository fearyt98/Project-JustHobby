package com.aurimteam.justhobby.user.course_info.course_reviews

import com.aurimteam.justhobby.response.ReviewResponse

class CourseReviewsPresenter(private var view: ICourseReviewsView?, private val model: ICourseReviewsModel?) :
    CourseReviewsModel.OnFinishedListener {

    override fun onResultSuccess(courseReviews: List<ReviewResponse>) {
        view?.showCourseReviews(courseReviews)
    }

    override fun onResultFail(strError: String?) {

    }
    fun getCourseReviews(){
        model?.getCourseReviewsData(this)
    }

    fun onDestroy(){
        view = null
    }
}