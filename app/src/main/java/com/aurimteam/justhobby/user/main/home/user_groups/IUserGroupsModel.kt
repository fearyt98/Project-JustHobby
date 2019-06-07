package com.aurimteam.justhobby.user.main.home.user_groups

interface IUserGroupsModel {
    fun getUserCoursesData(token: String, onFinishedListener: UserGroupsModel.OnFinishedListener)
    fun deleteUserGroup(token: String, groupId: Long, position: Int, OnFinishedListener: UserGroupsModel.OnFinishedListener)
}