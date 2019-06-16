package com.aurimteam.justhobby.user.main.notifications

import com.aurimteam.justhobby.response.IncludedResponse
import com.aurimteam.justhobby.response.NotificationResponse

interface INotificationsView {
    fun showNewNotifications(notifications: List<NotificationResponse>, included: IncludedResponse?)
    fun showOldNotifications(notifications: List<NotificationResponse>, included: IncludedResponse?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun showMessage(message: String?)
    fun showClear()
}