package com.aurimteam.justhobby.user.main.notifications

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.NotificationResponse
import com.aurimteam.justhobby.response.NotificationsResponse
import kotlinx.android.synthetic.main.fragment_main_notifications.*

class NotificationsFragment : Fragment(), INotificationsView {

    private val presenter = NotificationsPresenter(this, NotificationsModel())
    private var adapterOldNotify = NotificationsAdapter()
    private var adapterNewNotify = NotificationsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_notifications, container, false)
        view.findViewById<ImageButton>(R.id.notificationsBtnClear).setOnClickListener { deleteNotifications() }
        return view
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if(context != null)
            presenter.getNotifications(context!!)
        notificationsBtnClear.setOnClickListener { deleteNotifications() }
        notificationsNewRecyclerView.layoutManager = LinearLayoutManager(context)
        notificationsNewRecyclerView.adapter = adapterNewNotify
        notificationsOldRecyclerView.layoutManager = LinearLayoutManager(context)
        notificationsOldRecyclerView.adapter = adapterOldNotify
        ViewCompat.setNestedScrollingEnabled(notificationsNew, false)
        ViewCompat.setNestedScrollingEnabled(notificationsOld, false)
        notificationsClear.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showClear() {
        notificationsProgressBar.visibility = View.GONE
        notificationsContent.visibility = View.GONE
        notificationsClear.visibility = View.VISIBLE
    }

    override fun showNewNotifications(notifications: NotificationsResponse) {
        if(notifications.data.isEmpty()) {
            notificationsNew.visibility = View.GONE
            if(notificationsOld.visibility == View.GONE)
                showClear()
        } else {
            adapterNewNotify.onDataChange(notifications.data, notifications.included)
        }
    }

    override fun showOldNotifications(notifications: NotificationsResponse) {
        toggleContentPB(false)
        if(notifications.data.isEmpty()) {
            notificationsOld.visibility = View.GONE
            if(notificationsNew.visibility == View.GONE)
                showClear()
        } else {
            adapterOldNotify.onDataChange(notifications.data, notifications.included)
        }
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun toggleContentPB(isVisiblePB: Boolean) {
        if (isVisiblePB) {
            notificationsProgressBar.visibility = View.VISIBLE
            notificationsContent.visibility = View.GONE
        } else {
            notificationsProgressBar.visibility = View.GONE
            notificationsContent.visibility = View.VISIBLE
        }
    }

    private fun deleteNotifications() {
        if(context != null)
            presenter.deleteAll(context!!)
    }
}
