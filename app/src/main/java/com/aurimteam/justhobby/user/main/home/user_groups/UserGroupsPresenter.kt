package com.aurimteam.justhobby.user.main.home.user_groups

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.GroupResponse
import com.aurimteam.justhobby.response.IncludedResponse

class UserGroupsPresenter(private var view: IUserGroupsView?, private val model: IUserGroupsModel?) :
    UserGroupsModel.OnFinishedListener {

    override fun onResultSuccess(userGroups: List<GroupResponse>, included: IncludedResponse?) {
        view?.showUserCourses(userGroups, included)
    }

    override fun deletedUserGroup(position: Int) {
        view?.deletedUserGroup(position)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    fun deleteUserGroup(context: Context, groupId: Long, position: Int) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.deleteUserGroup(token, groupId, position, this)
    }

    fun getUserCourses(context: Context) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getUserCoursesData(token, this)
        }
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: IUserGroupsView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}