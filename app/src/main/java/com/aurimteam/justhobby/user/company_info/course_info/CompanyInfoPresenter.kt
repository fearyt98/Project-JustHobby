package com.aurimteam.justhobby.user.company_info.course_info

import com.aurimteam.justhobby.response.CourseResponse

class CompanyInfoPresenter(private var view: ICompanyInfoView?, private val model: ICompanyInfoModel?) :
    CompanyInfoModel.OnFinishedListener {

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