package com.aurimteam.justhobby.start.registry.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.aurimteam.justhobby.start.features.FeaturesActivity
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_registry.*
import kotlinx.android.synthetic.main.activity_registry_start.*

class RegistryStartActivity : AppCompatActivity(), IRegistryStartView {

    private val presenter = RegistryStartPresenter(this, RegistryStartModel(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry_start)
        findViewById<Button>(R.id.registryStartReadyBtn).setOnClickListener { readyBtnClick() }
    }

    override fun onStart() {
        super.onStart()
        if(presenter.isSetView())
            presenter.attachViewContext(this, this)
    }

    override fun onStop() {
        super.onStop()
        presenter.onDestroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun clearFirstName(message: String) {
        firstNameErrorRegistryStart.text = message
    }

    override fun clearLastName(message: String) {
        lastNameErrorRegistryStart.text = message
    }

    override fun hideErrors() {
        firstNameErrorRegistryStart.text = ""
        lastNameErrorRegistryStart.text = ""
    }

    override fun changeLengthFirstName(message: String) {
        firstNameErrorRegistryStart.text = message
    }

    override fun changeLengthLastName(message: String) {
        lastNameErrorRegistryStart.text = message
    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun userRegistered() {
        startActivity(Intent(this, FeaturesActivity::class.java))
    }

    override fun togglePB(isVisiblePB: Boolean) {
        if (isVisiblePB) registryStartProgressBar.visibility = View.VISIBLE
        else registryStartProgressBar.visibility = View.GONE
    }

    private fun readyBtnClick() {
        presenter.sendUserInfo(
            registryStartFirstName.text.toString(),
            registryStartLastName.text.toString()
        )
    }
}