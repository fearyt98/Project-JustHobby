package com.aurimteam.justhobby.Main.Notifications

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.NotificationResponse
import kotlinx.android.synthetic.main.activity_notifications_fragment.*

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
        val view = inflater.inflate(R.layout.activity_notifications_fragment, container, false)
        view.findViewById<ImageButton>(R.id.btnDeleteNotifications).setOnClickListener { deleteNotifications() }
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getNotifications()
        newNotifications.layoutManager = LinearLayoutManager(context)
        newNotifications.adapter = adapterNewNotify
        oldNotifications.layoutManager = LinearLayoutManager(context)
        oldNotifications.adapter = adapterOldNotify
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun deleteNotifications() {

    }
}
