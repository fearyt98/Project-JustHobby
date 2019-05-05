package com.aurimteam.justhobby.Main.Notifications

import com.aurimteam.justhobby.Response.NotificationResponse

interface INotificationsView {
    fun showNewNotifications(notifications: List<NotificationResponse>)
    fun showOldNotifications(notifications: List<NotificationResponse>)
}