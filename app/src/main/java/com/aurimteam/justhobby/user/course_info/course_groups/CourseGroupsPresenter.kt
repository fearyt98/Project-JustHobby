package com.aurimteam.justhobby.user.course_info.course_groups

import com.aurimteam.justhobby.response.GroupResponse

class CourseGroupsPresenter(private var view: ICourseGroupsView?, private val model: ICourseGroupsModel?) :
    CourseGroupsModel.OnFinishedListener {

    override fun onResultFail() {

    }

    override fun onResultSuccess(courseAllGroups: List<GroupResponse>) {
        view?.showCourseAllGroupse(courseAllGroups)
    }

    fun getCourseAllGroups() {
        model?.getCourseAllGroupsData(this)
    }

    fun onDestroy() {
        view = null
    }
}