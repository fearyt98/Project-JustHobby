package com.aurimteam.justhobby.user.main.notifications

interface INotificationsModel {
    fun getNotificationsData(token: String, onFinishedListener: NotificationsModel.OnFinishedListener)
    fun deleteAllNotify(token: String, onFinishedListener: NotificationsModel.OnFinishedListener)
}