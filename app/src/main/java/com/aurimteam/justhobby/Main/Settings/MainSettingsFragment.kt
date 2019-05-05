package com.aurimteam.justhobby.Main.Settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.aurimteam.justhobby.Main.Home.HomeTimeLineFragment
import com.aurimteam.justhobby.R

class MainSettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_main_settings_fragment, container, false)
        view.findViewById<LinearLayout>(R.id.notificationItemSettings).setOnClickListener { notificationItemClick() }
        view.findViewById<LinearLayout>(R.id.accountItemSettings).setOnClickListener { accountItemClick() }
        view.findViewById<LinearLayout>(R.id.feedbackItemSettings).setOnClickListener { feedbackItemClick() }
        view.findViewById<LinearLayout>(R.id.aboutItemSettings).setOnClickListener { aboutItemClick() }
        view.findViewById<LinearLayout>(R.id.leaveItemSettings).setOnClickListener { leaveItemClick() }
        return view
    }

    private fun notificationItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, HomeTimeLineFragment())
            .commit()

    }

    private fun accountItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, HomeTimeLineFragment())
            .commit()
    }

    private fun feedbackItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, HomeTimeLineFragment())
            .commit()

    }

    private fun aboutItemClick() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, HomeTimeLineFragment())
            .commit()

    }

    private fun leaveItemClick() {

    }
}
