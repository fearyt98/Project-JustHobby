package com.aurimteam.justhobby.user.main.home.user_courses

interface IUserCoursesModel {
    fun getUserCoursesData(token: String, onFinishedListener: UserCoursesModel.OnFinishedListener)
}