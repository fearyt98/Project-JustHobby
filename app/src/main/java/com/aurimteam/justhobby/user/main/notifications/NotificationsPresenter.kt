package com.aurimteam.justhobby.user.main.notifications

import com.aurimteam.justhobby.response.NotificationResponse

class NotificationsPresenter(private var view: INotificationsView?, private val model: INotificationsModel?) :
    NotificationsModel.OnFinishedListener {

    private var newNotifications: MutableList<NotificationResponse> = mutableListOf()
    private var oldNotifications: MutableList<NotificationResponse> = mutableListOf()

    override fun onResultSuccess(notifications: List<NotificationResponse>) {
        for (item in notifications)
            if (item.new) {
                newNotifications.add(item)
                item.new = false
            } else oldNotifications.add(item)
        if (newNotifications.size != 0) view?.showNewNotifications(newNotifications)
        if (oldNotifications.size != 0) view?.showOldNotifications(oldNotifications)
    }

    override fun onResultFail(strError: String?) {

    }

    fun getNotifications() {
        model?.getNotificationsData(this)
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: INotificationsView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}