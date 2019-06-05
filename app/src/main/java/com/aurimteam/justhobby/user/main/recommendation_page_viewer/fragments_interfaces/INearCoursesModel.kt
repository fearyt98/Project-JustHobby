package com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces

import com.aurimteam.justhobby.user.main.recommendation_page_viewer.near_user_courses.NearUserCoursesModel

interface INearCoursesModel {
    fun getNearCoursesData(onFinishedListener: NearUserCoursesModel.OnFinishedListener)
}