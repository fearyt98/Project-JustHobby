package com.aurimteam.justhobby.user.course_info.course_groups

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.group.IGroupPresenter
import com.aurimteam.justhobby.response.GroupResponse

class CourseGroupsPresenter(private var view: ICourseGroupsView?, private val model: ICourseGroupsModel?) :
    CourseGroupsModel.OnFinishedListener, IGroupPresenter {

    override fun onResultSuccess(groups: List<GroupResponse>) {
        view?.showCourseGroups(groups)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    override fun deletedUserGroup(position: Int) {
        view?.deletedUserGroup(position)
    }

    override fun addedUserGroup(position: Int) {
        view?.addedUserGroup(position)
    }

    override fun deleteUserGroup(context: Context, groupId: Long, position: Int) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.deleteUserGroup(token, groupId, position, this)
    }

    override fun addUserGroup(context: Context, groupId: Long, position: Int) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.addUserGroup(token, groupId, position, this)
    }

    fun getCourseGroups(context: Context, courseId: Long) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getCourseGroupsData(token, courseId, this)
        }
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: ICourseGroupsView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}