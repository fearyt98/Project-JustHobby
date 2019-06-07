package com.aurimteam.justhobby.user.main.home.user_groups

import com.aurimteam.justhobby.response.GroupResponse
import com.aurimteam.justhobby.response.IncludedResponse

interface IUserGroupsView {
    fun showUserCourses(userGroups: List<GroupResponse>, included: IncludedResponse?)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun deletedUserGroup(position: Int)
}