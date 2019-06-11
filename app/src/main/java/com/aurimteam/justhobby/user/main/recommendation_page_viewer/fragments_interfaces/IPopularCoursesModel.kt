package com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces

import com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses.PopularCoursesModel

interface IPopularCoursesModel {
    fun getPopularCoursesData(
        token: String,
        lat: Float?,
        lon: Float?,
        onFinishedListener: PopularCoursesModel.OnFinishedListener
    )
    fun deleteUserBookmark(token: String, courseId: Long, position: Int, onFinishedListener: PopularCoursesModel.OnFinishedListener)
    fun addUserBookmark(token: String, courseId: Long, position: Int, onFinishedListener: PopularCoursesModel.OnFinishedListener)

}