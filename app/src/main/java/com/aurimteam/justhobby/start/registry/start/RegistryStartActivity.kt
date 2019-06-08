package com.aurimteam.justhobby.start.registry.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.aurimteam.justhobby.start.features.FeaturesActivity
import com.aurimteam.justhobby.R
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

    override fun userRegistrited() {
        val intent = Intent(this, FeaturesActivity::class.java)
        startActivity(intent)
    }

    fun readyBtnClick() {
        if (arguments != null
            && registryStartFirstName.text.toString() != " "
            && registryStartLastName.text.toString() != " "
        ) {
            presenter.sendUserInfo(
                registryStartFirstName.text.toString(),
                registryStartLastName.text.toString(),
                arguments?.get("email").toString(),
                arguments?.get("password").toString(),
                arguments?.get("confirm_password").toString()
            )

        }
    }
}