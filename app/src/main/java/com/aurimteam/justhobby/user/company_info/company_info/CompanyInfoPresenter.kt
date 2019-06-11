package com.aurimteam.justhobby.user.company_info.company_info

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.course.ICoursePresenter
import com.aurimteam.justhobby.response.CompanyResponse
import com.aurimteam.justhobby.response.CompanyResponseOneR
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

class CompanyInfoPresenter(private var view: ICompanyInfoView?, private val model: ICompanyInfoModel?) :
    CompanyInfoModel.OnFinishedListener, ICoursePresenter {

    override fun onResultSuccessCompanyCourses(courses: List<CourseResponseR>, included: IncludedResponse?) {
        view?.showCompanyCourses(courses, included)
    }

    override fun onResultSuccessCompany(company: CompanyResponseOneR) {
        view?.bindCompanyInfo(company)
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
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getCompanyCoursesData(token, companyId, lat, lon, this)
        }
    }

    fun getCompany(context: Context, companyId: Long) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getCompanyData(token, companyId, this)
        }
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: ICompanyInfoView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}