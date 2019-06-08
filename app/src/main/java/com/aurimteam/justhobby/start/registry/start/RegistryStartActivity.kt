package com.aurimteam.justhobby.start.registry.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.aurimteam.justhobby.start.features.FeaturesActivity
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_registry.*
import kotlinx.android.synthetic.main.activity_registry_start.*

class RegistryStartActivity : AppCompatActivity(), IRegistryStartView {

    private val presenter = RegistryStartPresenter(this, RegistryStartModel(), this)
    private var arguments: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry_start)
        findViewById<Button>(R.id.registryStartReadyBtn).setOnClickListener { readyBtnClick() }
        arguments = intent.extras
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun userRegistered() {
        startActivity(Intent(this, FeaturesActivity::class.java))
    }

    override fun togglePB(isVisiblePB: Boolean) {
        if (isVisiblePB) {
            registryStartProgressBar.visibility = View.VISIBLE
        } else {
            registryStartProgressBar.visibility = View.GONE
        }
    }

    private fun readyBtnClick() {
        presenter.sendUserInfo(
            registryStartFirstName.text.toString(),
            registryStartLastName.text.toString()
        )
    }
}