package com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces

import com.aurimteam.justhobby.user.main.recommendation_page_viewer.near_user_courses.NearUserCoursesModel

interface INearCoursesModel {
    fun getNearCoursesData(
        token: String,
        lat: Float?,
        lon: Float?,
        onFinishedListener: NearUserCoursesModel.OnFinishedListener
    )
    fun deleteUserBookmark(token: String, courseId: Long, position: Int, onFinishedListener: NearUserCoursesModel.OnFinishedListener)
    fun addUserBookmark(token: String, courseId: Long, position: Int, onFinishedListener: NearUserCoursesModel.OnFinishedListener)

}