package com.aurimteam.justhobby.user.main.settings

interface ISettingsModel {
    fun logoutUser(token: String, onFinishedListener: SettingsModel.OnFinishedListener)
}