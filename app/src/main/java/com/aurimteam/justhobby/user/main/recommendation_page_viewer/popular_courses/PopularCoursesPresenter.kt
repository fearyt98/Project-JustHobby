package com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses

import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.IPopularCoursesModel
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.IPopularCoursesView
import com.aurimteam.justhobby.response.CourseResponse

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