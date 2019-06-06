package com.aurimteam.justhobby.user.main.main_nav

interface IMainNavModel {
    fun getUser(token: String, onFinishedListener: MainNavModel.OnFinishedListener)
}