package com.aurimteam.justhobby.user.course_info.course_reviews

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.ReviewResponse

class CourseReviewsPresenter(private var view: ICourseReviewsView?, private val model: ICourseReviewsModel?) :
    CourseReviewsModel.OnFinishedListener {

    private var context: Context? = null
    override fun onResultSuccess(courseReviews: List<ReviewResponse>) {
        var hideBtnReview = false
        loop@ for (item in courseReviews)
            if (item.user_id == Settings(context!!).getProperty("user_id")!!.toInt()) {
                hideBtnReview = true
                break@loop
            }
        if (hideBtnReview) view?.hideBtnForReview(true)
        else view?.hideBtnForReview(false)
        view?.showCourseReviews(courseReviews)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    fun getCourseReviews(context: Context, courseId: Long) {
        this.context = context
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