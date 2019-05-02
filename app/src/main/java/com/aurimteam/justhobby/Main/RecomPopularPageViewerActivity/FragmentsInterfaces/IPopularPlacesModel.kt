package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces

import com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularCourses.PopularCoursesModel

interface IPopularCoursesModel {
    fun getPopularCoursesData(onFinishedListener: PopularCoursesModel.OnFinishedListener)
}