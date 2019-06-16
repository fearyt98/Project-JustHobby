package com.aurimteam.justhobby.user.main.notifications

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.NotificationResponse
import com.aurimteam.justhobby.response.NotificationsResponse

class NotificationsPresenter(private var view: INotificationsView?, private val model: INotificationsModel?) :
    NotificationsModel.OnFinishedListener {

    override fun onResultSuccess(notifications: NotificationsResponse) {
        val newNotify = mutableListOf<NotificationResponse>()
        val oldNotify = mutableListOf<NotificationResponse>()
        if (notifications.data.isNotEmpty())
            for (item in notifications.data)
                if (!item.attributes.is_showed) newNotify.add(item)
                else oldNotify.add(item)
        view?.showNewNotifications(newNotify, notifications.included)
        view?.showOldNotifications(oldNotify, notifications.included)
        view?.toggleContentPB(false)
    }

    override fun onResultSuccessDelete() {
        view?.showClear()
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    fun getNotifications(context: Context) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getNotificationsData(token, this)
        }
    }

    fun deleteAll(context: Context) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            model?.deleteAllNotify(token, this)
        }
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