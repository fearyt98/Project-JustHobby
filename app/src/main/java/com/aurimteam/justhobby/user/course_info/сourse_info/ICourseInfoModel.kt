package com.aurimteam.justhobby.user.course_info.сourse_info

interface ICourseInfoModel {
    fun getCourseGroupsData(token: String, courseId: Long, onFinishedListener: CourseInfoModel.OnFinishedListener)
    fun getCourseData(token: String, courseId: Long, onFinishedListener: CourseInfoModel.OnFinishedListener)
    fun deleteUserGroup(token: String, groupId: Long, position: Int, onFinishedListener: CourseInfoModel.OnFinishedListener)
    fun addUserGroup(token: String, groupId: Long, position: Int, onFinishedListener: CourseInfoModel.OnFinishedListener)
    fun deleteUserBookmark(token: String, courseId: Long, onFinishedListener: CourseInfoModel.OnFinishedListener)
    fun addUserBookmark(token: String, courseId: Long, onFinishedListener: CourseInfoModel.OnFinishedListener)
}