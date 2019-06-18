package com.aurimteam.justhobby.user.main.notifications

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.IncludedResponse
import com.aurimteam.justhobby.response.NotificationResponse
import com.aurimteam.justhobby.response.NotificationsResponse
import com.aurimteam.justhobby.user.search.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_main_notifications.*

class NotificationsFragment : Fragment(), INotificationsView {

    private val presenter = NotificationsPresenter(this, NotificationsModel())
    private var adapterOldNotify = NotificationsAdapter()
    private var adapterNewNotify = NotificationsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_notifications, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (context != null)
            presenter.getNotifications(context!!)
        notificationsBtnClear.setOnClickListener { deleteNotifications() }
        notificationsClearBtn.setOnClickListener { openSearch() }
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

    override fun showNewNotifications(notifications: List<NotificationResponse>, included: IncludedResponse?) {
        if (notifications.isEmpty() || included == null) {
            notificationsNew.visibility = View.GONE
            if (notificationsOld.visibility == View.GONE)
                showClear()
        } else {
            adapterNewNotify.onDataChange(notifications,  included)
        }
    }

    override fun showOldNotifications(notifications: List<NotificationResponse>, included: IncludedResponse?) {
        if (notifications.isEmpty() || included == null) {
            notificationsOld.visibility = View.GONE
            if (notificationsNew.visibility == View.GONE)
                showClear()
        } else {
            adapterOldNotify.onDataChange(notifications,  included)
        }
    }

    override fun showMessage(message: String?) {
        val devMode = Settings(context!!).getPropertyBoolean("dev_mode", false)
        if (devMode != null && devMode) {
            val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 30)
            toast.show()
        }
        toggleContentPB(false)
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

    private fun openSearch() {
        activity?.findViewById<BottomNavigationView>(R.id.mainNavNavigation)?.selectedItemId = R.id.navigation_search
        fragmentManager!!
            .beginTransaction()
            .replace(R.id.mainNavContainerFragment, SearchFragment())
            .commit()
    }

    private fun deleteNotifications() {
        if (context != null)
            presenter.deleteAll(context!!)
    }
}
