package com.aurimteam.justhobby.user.main.notifications

import com.aurimteam.justhobby.response.NotificationsResponse

interface INotificationsView {
    fun showNewNotifications(notifications: NotificationsResponse)
    fun showOldNotifications(notifications: NotificationsResponse)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun showMessage(message: String?)
    fun showClear()
}