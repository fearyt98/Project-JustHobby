package com.aurimteam.justhobby.CoursesInfo.CourseInfoActivity

import com.aurimteam.justhobby.Response.GroupResponse

class CourseInfoPresenter(private var view: ICourseInfoView?, private val model: ICourseInfoModel?) :
    CourseInfoModel.onFinishedListener {

    override fun onResultSuccess(groups: List<GroupResponse>) {
        view?.showCourseGroups(groups)
    }

    override fun onResultFail() {

    }

    fun getCourseGroups() {
        model?.getCourseGroupsData(this)
    }

    fun onDestroy() {
        view = null
    }

}