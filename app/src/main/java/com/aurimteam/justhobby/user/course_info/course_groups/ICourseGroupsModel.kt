package com.aurimteam.justhobby.user.course_info.course_groups

interface ICourseGroupsModel {
    fun getCourseGroupsData(token: String, courseId: Long, onFinishedListener: CourseGroupsModel.OnFinishedListener)
    fun deleteUserGroup(token: String, groupId: Long, position: Int, onFinishedListener: CourseGroupsModel.OnFinishedListener)
    fun addUserGroup(token: String, groupId: Long, position: Int, onFinishedListener: CourseGroupsModel.OnFinishedListener)
}