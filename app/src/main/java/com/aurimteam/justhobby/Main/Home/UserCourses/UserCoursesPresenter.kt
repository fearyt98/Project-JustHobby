package com.aurimteam.justhobby.Main.Home.UserCourses

import com.aurimteam.justhobby.Response.UserCourseResponse

class UserCoursesPresenter(private var view: IUserCoursesView?, private val model: IUserCoursesModel?) :
    UserCoursesModel.onFinishedListener {

    override fun onResultSuccess(userCourses: List<UserCourseResponse>) {
        view?.showUserCourses(userCourses)
    }

    override fun onResultFail() {

    }

    fun getUserCourses() {
        model?.getUserCoursesData(this)
    }

    fun onDestroy() {
        view = null
    }
}