package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.FragmentsInterfaces

import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.PopularCourses.PopularCoursesModel

interface IPopularCoursesModel {
    fun getPopularCoursesData(onFinishedListener: PopularCoursesModel.OnFinishedListener)
}