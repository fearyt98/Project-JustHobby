package com.aurimteam.justhobby.user.course_info.сourse_info

import com.aurimteam.justhobby.response.GroupResponse

class CourseInfoPresenter(private var view: ICourseInfoView?, private val model: ICourseInfoModel?) :
    CourseInfoModel.OnFinishedListener {

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