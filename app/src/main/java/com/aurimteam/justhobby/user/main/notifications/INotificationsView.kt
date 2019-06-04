package com.aurimteam.justhobby.user.main.notifications

import com.aurimteam.justhobby.response.NotificationResponse

interface INotificationsView {
    fun showNewNotifications(notifications: List<NotificationResponse>)
    fun showOldNotifications(notifications: List<NotificationResponse>)
}