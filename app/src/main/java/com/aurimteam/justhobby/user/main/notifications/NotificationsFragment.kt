package com.aurimteam.justhobby.user.main.notifications

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.NotificationResponse
import kotlinx.android.synthetic.main.fragment_main_notifications.*

class NotificationsFragment : Fragment(), INotificationsView {

    private val presenter = NotificationsPresenter(this, NotificationsModel())
    private var adapterOldNotify = NotificationsOldAdapter()
    private var adapterNewNotify = NotificationsNewAdapter()

    override fun showNewNotifications(notifications: List<NotificationResponse>) {
        adapterNewNotify.onDataChange(notifications)
    }

    override fun showOldNotifications(notifications: List<NotificationResponse>) {
        adapterOldNotify.onDataChange(notifications)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_notifications, container, false)
        view.findViewById<ImageButton>(R.id.notificationsBtnClear).setOnClickListener { deleteNotifications() }
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getNotifications()
        notificationsNew.layoutManager = LinearLayoutManager(context)
        notificationsNew.adapter = adapterNewNotify
        notificationsOld.layoutManager = LinearLayoutManager(context)
        notificationsOld.adapter = adapterOldNotify
        ViewCompat.setNestedScrollingEnabled(notificationsNew, false)
        ViewCompat.setNestedScrollingEnabled(notificationsOld, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun deleteNotifications() {

    }
}
