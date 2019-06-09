package com.aurimteam.justhobby.user.course_info.course_groups

import com.aurimteam.justhobby.response.GroupResponse

interface ICourseGroupsView{
    fun showCourseGroups(groups: List<GroupResponse>)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun deletedUserGroup(position: Int)
    fun addedUserGroup(position: Int)
}