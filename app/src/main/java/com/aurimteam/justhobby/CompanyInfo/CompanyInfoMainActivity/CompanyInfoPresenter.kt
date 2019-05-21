package com.aurimteam.justhobby.CompanyInfo.CompanyInfoMainActivity

import com.aurimteam.justhobby.Response.CourseResponse

class CompanyInfoPresenter(private var view: ICompanyInfoView?, private val model: ICompanyInfoModel?) :
    CompanyInfoModel.onFinishedListener {

    override fun onResultSuccess(courses: List<CourseResponse>) {
        view?.showCompanyCourses(courses)
    }

    override fun onResultFail() {

    }

    fun getCompanyCourses() {
        model?.getCompanyCoursesData(this)

    }

    fun onDestroy() {
        view = null
    }
}