package com.aurimteam.justhobby.user.main.settings.about_app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import android.content.Intent
import android.net.Uri
import android.view.Gravity
import android.widget.Toast
import com.aurimteam.justhobby.Settings
import kotlinx.android.synthetic.main.fragment_settings_about_app.*
import android.content.pm.PackageManager
import java.lang.Exception

class AboutAppFragment : Fragment() {

    private var countDevTap = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings_about_app, container, false)
    }

    override fun onStart() {
        super.onStart()
        aboutAppDevBtn.setOnClickListener { changeDev() }
        onSettingsBtnBack.setOnClickListener { back() }
        confidentPoliticBtn.setOnClickListener { openConfidentPolitics() }
        userAgreementBtn.setOnClickListener { openUserAgreement() }
        licensesBtn.setOnClickListener { openLicenses() }
        try {
            val pInfo = context?.packageManager?.getPackageInfo(activity?.packageName, 0)
            val version = pInfo?.versionName
            aboutAppVersion.text = String.format(context!!.resources.getString(R.string.version_app), version)
        } catch (e: Exception) {
            aboutAppVersion.text = String.format(context!!.resources.getString(R.string.version_app), ":)")
        }
    }

    private fun changeDev() {
        countDevTap++
        if (countDevTap >= 7) {
            var devMode = Settings(context!!).getPropertyBoolean("dev_mode", false)
            if (devMode != null)
                Settings(context!!).setPropertyBoolean("dev_mode", !devMode)
            else
                Settings(context!!).setPropertyBoolean("dev_mode", true)
            devMode = Settings(context!!).getPropertyBoolean("dev_mode", false)
            if (devMode != null && devMode)
                showMessage("Режим разработчика включен")
            else
                showMessage("Режим разработчика выключен")
            countDevTap = 0
        }
    }

    private fun showMessage(message: String?) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    private fun openConfidentPolitics() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        startActivity(browserIntent)
    }

    private fun openUserAgreement() {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://justhobby.herokuapp.com/public/files/User_Agreement.pdf"))
        startActivity(browserIntent)
    }

    private fun openLicenses() {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://justhobby.herokuapp.com/public/files/Software_Licenses.pdf"))
        startActivity(browserIntent)
    }

    private fun back() {
        fragmentManager?.popBackStack()
    }
}