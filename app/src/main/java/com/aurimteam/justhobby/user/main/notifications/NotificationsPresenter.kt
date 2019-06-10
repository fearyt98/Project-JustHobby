package com.aurimteam.justhobby.user.main.notifications

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.NotificationsResponse

class NotificationsPresenter(private var view: INotificationsView?, private val model: INotificationsModel?) :
    NotificationsModel.OnFinishedListener {

    override fun onResultSuccessNew(notifications: NotificationsResponse) {
        view?.showNewNotifications(notifications)
    }

    override fun onResultSuccessOld(notifications: NotificationsResponse) {
        view?.showOldNotifications(notifications)
    }

    override fun onResultSuccessDelete() {
        view?.showClear()
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    fun getNotifications(context: Context) {
        val token = Settings(context).getProperty("token")
        if(token != null) {
            view?.toggleContentPB(true)
            model?.getNotificationsData(token, this)
        }
    }

    fun deleteAll(context: Context) {
        val token = Settings(context).getProperty("token")
        if(token != null) {
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