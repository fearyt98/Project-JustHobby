package com.aurimteam.justhobby.user.course_info.сourse_info

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.group.IGroupPresenter
import com.aurimteam.justhobby.response.CourseResponseOneR
import com.aurimteam.justhobby.response.GroupResponse

class CourseInfoPresenter(private var view: ICourseInfoView?, private val model: ICourseInfoModel?) :
    CourseInfoModel.OnFinishedListener, IGroupPresenter {

    override fun onResultSuccessCourse(course: CourseResponseOneR) {
        view?.bindCourseInfo(course)
    }

    override fun onResultSuccessCourseGroups(groups: List<GroupResponse>) {
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

    override fun deletedUserBookmark() {
        view?.changeColorBtnBookmark(false)
    }

    override fun addedUserBookmark() {
        view?.changeColorBtnBookmark(true)
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

    fun deleteUserBookmark(context: Context, courseId: Long) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.deleteUserBookmark(token, courseId, this)
    }

    fun addUserBookmark(context: Context, courseId: Long) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.addUserBookmark(token, courseId, this)
    }

    fun getCourseGroups(context: Context, courseId: Long) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getCourseGroupsData(token, courseId, this)
        }
    }

    fun getCourse(context: Context, courseId: Long) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getCourseData(token, courseId, this)
        }
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: ICourseInfoView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

}