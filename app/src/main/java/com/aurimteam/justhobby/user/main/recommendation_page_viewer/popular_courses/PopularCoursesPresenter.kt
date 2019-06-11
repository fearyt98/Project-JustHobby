package com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.course.ICoursePresenter
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.IPopularCoursesModel
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.IPopularCoursesView
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

class PopularCoursesPresenter(private var view: IPopularCoursesView?, private val model: IPopularCoursesModel?) :
    PopularCoursesModel.OnFinishedListener, ICoursePresenter {

    override fun onResultSuccess(courses: List<CourseResponseR>, included: IncludedResponse?) {
        view?.showPopularCourses(courses, included)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    override fun deletedUserBookmark(position: Int) {
        view?.deletedUserBookmark(position)
    }

    override fun addedUserBookmark(position: Int) {
        view?.addedUserBookmark(position)
    }

    override fun deleteUserBookmark(context: Context, courseId: Long, position: Int) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.deleteUserBookmark(token, courseId, position, this)
    }

    override fun addUserBookmark(context: Context, courseId: Long, position: Int) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.addUserBookmark(token, courseId, position, this)
    }

    fun getPopularCourses(context: Context, lat: Float?, lon: Float?) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.getPopularCoursesData(token,lat, lon, this)
    }

    fun onDestroy() {
        view = null
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: IPopularCoursesView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}