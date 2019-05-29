package com.aurimteam.justhobby.Main.Settings

interface ISettingsModel {
    fun logoutUser(token: String, onFinishedListener: SettingsModel.OnFinishedListener)
}