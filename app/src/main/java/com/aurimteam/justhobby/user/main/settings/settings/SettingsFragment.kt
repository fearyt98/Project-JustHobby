package com.aurimteam.justhobby.user.main.settings.settings

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.aurimteam.justhobby.user.main.home.home.HomeTimelineFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.start.auth.AuthActivity
import android.view.Gravity
import android.widget.Toast
import com.aurimteam.justhobby.NotificationsService
import com.aurimteam.justhobby.user.main.settings.about_app.AboutAppFragment
import com.aurimteam.justhobby.user.main.settings.notifications.NotificationsFragment
import com.aurimteam.justhobby.user.main.settings.user_profile.UserProfileFragment

class SettingsFragment : Fragment(), ISettingsView {

    private var presenter: SettingsPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_settings, container, false)
        view.findViewById<LinearLayout>(R.id.settingsNotification).setOnClickListener { notificationItemClick() }
        view.findViewById<LinearLayout>(R.id.settingsAccount)
            .setOnClickListener { accountItemClick() }
        view.findViewById<LinearLayout>(R.id.settingsAbout).setOnClickListener { aboutItemClick() }
        view.findViewById<LinearLayout>(R.id.settingsLogout).setOnClickListener { leaveItemClick(view) }

        presenter = SettingsPresenter(
            this,
            SettingsModel(),
            container?.context
        )
        return view
    }

    override fun onStart() {
        super.onStart()
        if (presenter != null && context != null)
            if (presenter!!.isSetView())
                presenter!!.attachViewContext(this, context!!)
    }

    override fun onStop() {
        super.onStop()
        presenter?.onDestroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(
            activity,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    private fun notificationItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, NotificationsFragment())
            .commit()

    }

    private fun accountItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, UserProfileFragment())
            .commit()
    }

    private fun feedbackItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, HomeTimelineFragment())
            .commit()

    }

    private fun aboutItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, AboutAppFragment())
            .commit()

    }

    private fun leaveItemClick(view: View) {
        stopNotifyService(view)
        presenter?.logoutUser()
    }

    private fun stopNotifyService(view: View) {
        val serviceIntent = Intent(view.context, NotificationsService::class.java)
        activity?.stopService(serviceIntent)
    }

    override fun openAuth() {
        startActivity(Intent(activity, AuthActivity::class.java))
    }
}
