package com.aurimteam.justhobby.user.main.home.user_courses

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.response.CourseResponse

class UserCoursesPresenter(private var view: IUserCoursesView?, private val model: IUserCoursesModel?) :
    UserCoursesModel.OnFinishedListener {

    override fun onResultSuccess(userCourses: List<CategoryResponse>, included: List<CourseResponse>) {
        view?.showUserCourses(userCourses, included)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    fun getUserCourses(context: Context) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getUserCoursesData(token, this)
        }
    }

    fun onDestroy() {
        view = null
    }
}