package com.aurimteam.justhobby.CoursesInfo.CourseGroupsActivity

import com.aurimteam.justhobby.Response.GroupResponse

class CourseGroupsPresenter(private var view: ICourseGroupsView?, private val model: ICourseGroupsModel?) :
    CourseGroupsModel.onFinishedListener {

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