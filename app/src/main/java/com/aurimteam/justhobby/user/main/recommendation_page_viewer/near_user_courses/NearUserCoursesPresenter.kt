package com.aurimteam.justhobby.user.main.recommendation_page_viewer.near_user_courses

import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.INearCoursesModel
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.INearCoursesView
import com.aurimteam.justhobby.response.CourseResponse

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