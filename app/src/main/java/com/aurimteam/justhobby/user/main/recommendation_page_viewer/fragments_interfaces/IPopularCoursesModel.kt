package com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces

import com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses.PopularCoursesModel

interface IPopularCoursesModel {
    fun getPopularCoursesData(onFinishedListener: PopularCoursesModel.OnFinishedListener)
}