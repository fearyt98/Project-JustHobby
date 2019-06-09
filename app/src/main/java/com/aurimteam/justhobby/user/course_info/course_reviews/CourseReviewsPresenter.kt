package com.aurimteam.justhobby.user.course_info.course_reviews

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.ReviewResponse

class CourseReviewsPresenter(private var view: ICourseReviewsView?, private val model: ICourseReviewsModel?) :
    CourseReviewsModel.OnFinishedListener {

    override fun onResultSuccess(courseReviews: List<ReviewResponse>) {
        view?.showCourseReviews(courseReviews)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    fun getCourseReviews(context: Context, courseId: Long) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getCourseReviewsData(token, courseId, this)
        }
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: ICourseReviewsView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

}