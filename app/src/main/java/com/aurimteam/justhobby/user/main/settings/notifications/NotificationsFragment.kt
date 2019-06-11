package com.aurimteam.justhobby.user.main.settings.notifications

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.R
import android.widget.CompoundButton
import com.aurimteam.justhobby.Settings

class NotificationsFragment : Fragment() {

    private var isTouched = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings_notification, container, false)
        view.findViewById<ImageButton>(R.id.settingsNotificationBtnBack).setOnClickListener { back() }

        val mute = Settings(context!!).getPropertyBoolean("mute", false)
        view.findViewById<SwitchCompat>(R.id.changeMuteSwitch).isChecked = mute == true

        view.findViewById<SwitchCompat>(R.id.changeMuteSwitch)
            .setOnTouchListener(View.OnTouchListener { view, motionEvent ->
                isTouched = true
                false
            })
        view.findViewById<SwitchCompat>(R.id.changeMuteSwitch)
            .setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isTouched) {
                    isTouched = false
                    if (isChecked) {
                        setMute()
                    } else {
                        unsetMute()
                    }
                }
            })
        return view
    }

    private fun setMute() {
        Settings(context!!).setPropertyBoolean("mute", true)
    }

    private fun unsetMute() {
        Settings(context!!).setPropertyBoolean("mute", false)
    }

    private fun back() {
        fragmentManager?.popBackStack()
    }
}