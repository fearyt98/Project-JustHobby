package com.aurimteam.justhobby.user.main.settings.settings

interface ISettingsModel {
    fun logoutUser(token: String, onFinishedListener: SettingsModel.OnFinishedListener)
}