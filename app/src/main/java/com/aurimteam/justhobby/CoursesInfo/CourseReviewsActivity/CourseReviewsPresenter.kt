package com.aurimteam.justhobby.CoursesInfo.CourseReviewsActivity

import android.view.View
import com.aurimteam.justhobby.Response.CourseReviewsResponse

class CourseReviewsPresenter(private var view: ICourseReviewsView?, private val model: ICourseReviewsModel?) :
    CourseReviewsModel.onFinishedListener {

    override fun onResultSuccess(courseReviews: List<CourseReviewsResponse>) {
        view?.showCourseReviews(courseReviews)
    }

    override fun onResultFail() {

    }
    fun getCourseReviews(){
        model?.getCourseReviewsData(this)
    }

    fun onDestroy(){
        view = null
    }
}