package com.aurimteam.justhobby.Main.RecommendationPageViewer.FragmentsInterfaces

import com.aurimteam.justhobby.Main.RecommendationPageViewer.NearUserCourses.NearUserCoursesModel

interface INearCoursesModel {
    fun getNearCoursesData(onFinishedListener: NearUserCoursesModel.OnFinishedListener)
}