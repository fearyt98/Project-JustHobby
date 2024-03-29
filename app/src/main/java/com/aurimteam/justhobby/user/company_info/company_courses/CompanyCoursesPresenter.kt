package com.aurimteam.justhobby.user.company_info.company_courses

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.course.ICoursePresenter
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

class CompanyCoursesPresenter(private var view: ICompanyCoursesView?, private val model: ICompanyCoursesModel?) :
    CompanyCoursesModel.OnFinishedListener, ICoursePresenter {

    override fun onResultSuccess(courses: List<CourseResponseR>, included: IncludedResponse?) {
        view?.showCompanyCourses(courses, included)
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

    fun getCompanyCourses(context: Context, companyId: Long, lat: Float?, lon: Float?) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.getCompanyCoursesData(token, companyId, lat, lon, this)
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: ICompanyCoursesView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}