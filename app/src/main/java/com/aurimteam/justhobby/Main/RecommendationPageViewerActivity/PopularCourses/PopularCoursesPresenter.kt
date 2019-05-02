package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.PopularCourses

import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.FragmentsInterfaces.IPopularCoursesModel
import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.FragmentsInterfaces.IPopularCoursesView
import com.aurimteam.justhobby.Response.CourseResponse

class PopularCoursesPresenter(private var view: IPopularCoursesView?, private val model: IPopularCoursesModel?) :
    PopularCoursesModel.OnFinishedListener {

    override fun onResultSuccess(courses: List<CourseResponse>) {
        view?.showPopularCourses(courses)
    }

    override fun onResultFail() {

    }

    fun getPopularCourses() {
        model?.getPopularCoursesData(this)
    }

    fun onDestroy() {
        view = null
    }
}