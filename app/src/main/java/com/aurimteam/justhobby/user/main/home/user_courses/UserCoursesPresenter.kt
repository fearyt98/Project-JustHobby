package com.aurimteam.justhobby.user.main.home.user_courses

import com.aurimteam.justhobby.response.UserCourseResponse

class UserCoursesPresenter(private var view: IUserCoursesView?, private val model: IUserCoursesModel?) :
    UserCoursesModel.OnFinishedListener {

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