package com.aurimteam.justhobby.user.company_info.company_courses

import com.aurimteam.justhobby.response.CourseResponse

class CompanyCoursesPresenter(private var view: ICompanyCoursesView?, private val model: ICompanyCoursesModel) :
    CompanyCoursesModel.OnFinishedListener {

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