package com.aurimteam.justhobby.Main.Settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.aurimteam.justhobby.Main.Home.HomeTimeLineFragment
import com.aurimteam.justhobby.R

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_settings, container, false)
        view.findViewById<LinearLayout>(R.id.settingsNotification).setOnClickListener { notificationItemClick() }
        view.findViewById<LinearLayout>(R.id.settingsAccount).setOnClickListener { accountItemClick() }
        view.findViewById<LinearLayout>(R.id.settingsAbout).setOnClickListener { aboutItemClick() }
        view.findViewById<LinearLayout>(R.id.settingsLogout).setOnClickListener { leaveItemClick() }
        return view
    }

    private fun notificationItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, HomeTimeLineFragment())
            .commit()

    }

    private fun accountItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, HomeTimeLineFragment())
            .commit()
    }

    private fun feedbackItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, HomeTimeLineFragment())
            .commit()

    }

    private fun aboutItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, HomeTimeLineFragment())
            .commit()

    }

    private fun leaveItemClick() {

    }
}
