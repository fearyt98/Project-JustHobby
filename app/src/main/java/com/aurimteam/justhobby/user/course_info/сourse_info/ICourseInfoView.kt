package com.aurimteam.justhobby.user.course_info.сourse_info

import com.aurimteam.justhobby.response.CourseResponseOneR
import com.aurimteam.justhobby.response.GroupResponse

interface ICourseInfoView {
    fun showCourseGroups(groups: List<GroupResponse>)
    fun bindCourseInfo(course: CourseResponseOneR)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun changeColorBtnBookmark(user: Boolean?)
    fun deletedUserGroup(position: Int)
    fun addedUserGroup(position: Int)
}