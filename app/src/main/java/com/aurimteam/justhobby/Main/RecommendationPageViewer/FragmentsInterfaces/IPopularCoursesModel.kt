package com.aurimteam.justhobby.Main.RecommendationPageViewer.FragmentsInterfaces

import com.aurimteam.justhobby.Main.RecommendationPageViewer.PopularCourses.PopularCoursesModel

interface IPopularCoursesModel {
    fun getPopularCoursesData(onFinishedListener: PopularCoursesModel.OnFinishedListener)
}