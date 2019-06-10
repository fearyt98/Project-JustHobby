package com.aurimteam.justhobby.user.main.settings.about_app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import android.content.Intent
import android.net.Uri
import android.widget.ImageButton
import android.widget.TextView

class AboutAppFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings_about_app, container, false)
        view.findViewById<ImageButton>(R.id.onSettingsBtnBack).setOnClickListener { }
        view.findViewById<TextView>(R.id.confidentPoliticBtn).setOnClickListener { openConfidentPolitics() }
        view.findViewById<TextView>(R.id.userAgreementBtn).setOnClickListener { openUserAgreement() }
        view.findViewById<TextView>(R.id.licensesBtn).setOnClickListener { openLicenses() }
        return view
    }

    private fun openConfidentPolitics() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        startActivity(browserIntent)
    }

    private fun openUserAgreement() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        startActivity(browserIntent)
    }

    private fun openLicenses() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        startActivity(browserIntent)
    }
}