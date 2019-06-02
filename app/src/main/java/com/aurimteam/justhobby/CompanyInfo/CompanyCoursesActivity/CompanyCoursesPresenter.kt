package com.aurimteam.justhobby.CompanyInfo.CompanyCoursesActivity

import com.aurimteam.justhobby.Response.CourseResponse

class CompanyCoursesPresenter(private var view: ICompanyCoursesView?, private val model: ICompanyCoursesModel) :
    CompanyCoursesModel.onFinishedListener {

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