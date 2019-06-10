package com.aurimteam.justhobby.user.course_info.course_review_new

import android.content.Context
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Settings

class CourseReviewNewPresenter(private var view: ICourseReviewNewView?, private val model: ICourseReviewNewModel?) :
    CourseReviewNewModel.OnFinishedListener {
    override fun onResultSuccess() {
        view?.back()
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)

    }

    override fun userHaveReview(strError: String?) {
        view?.showMessage("Вы уже оставили отзыв")
    }

    fun sendNewReview(courseId: Long, review: String, rating: Int, context: Context) {
        if (review == "") {
            view?.showError(context.getString(R.string.emptyField))
            return
        }
        if (review.length < 20) {
            view?.showError(context.getString(R.string.min_20_symbols))
            return
        }
        if (review.length > 500) {
            view?.showError(context.getString(R.string.min_500_symbols))
            return
        }

        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.sendNewReviewData(courseId, review, rating, token, this)
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: ICourseReviewNewView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}