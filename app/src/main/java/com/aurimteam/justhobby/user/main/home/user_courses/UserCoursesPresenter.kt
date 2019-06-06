package com.aurimteam.justhobby.user.main.home.user_courses

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.GroupResponse
import com.aurimteam.justhobby.response.IncludedResponse

class UserCoursesPresenter(private var view: IUserCoursesView?, private val model: IUserCoursesModel?) :
    UserCoursesModel.OnFinishedListener {

    override fun onResultSuccess(userCourses: List<GroupResponse>, included: IncludedResponse) {
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