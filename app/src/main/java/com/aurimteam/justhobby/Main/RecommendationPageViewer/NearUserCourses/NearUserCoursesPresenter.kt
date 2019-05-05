package com.aurimteam.justhobby.Main.RecommendationPageViewer.NearUserCourses

import com.aurimteam.justhobby.Main.RecommendationPageViewer.FragmentsInterfaces.INearCoursesModel
import com.aurimteam.justhobby.Main.RecommendationPageViewer.FragmentsInterfaces.INearCoursesView
import com.aurimteam.justhobby.Response.CourseResponse

class NearUserCoursesPresenter(private var view: INearCoursesView?, private val model: INearCoursesModel?) :
    NearUserCoursesModel.OnFinishedListener {

    override fun onResultSuccess(courses: List<CourseResponse>) {
        view?.showNearUserCourses(courses)
    }

    override fun onResultFail() {

    }

    fun getNearCourses() {
        model?.getNearCoursesData(this)
    }

    fun onDestroy() {
        view = null
    }
}