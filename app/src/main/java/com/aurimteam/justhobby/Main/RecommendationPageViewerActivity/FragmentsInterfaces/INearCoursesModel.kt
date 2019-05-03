package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.FragmentsInterfaces

import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.NearUserCourses.NearUserCoursesModel

interface INearCoursesModel {
    fun getNearCoursesData(onFinishedListener: NearUserCoursesModel.OnFinishedListener)
}