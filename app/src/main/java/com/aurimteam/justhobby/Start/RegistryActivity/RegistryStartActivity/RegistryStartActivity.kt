package com.aurimteam.justhobby.Start.RegistryActivity.RegistryStartActivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.aurimteam.justhobby.Start.FeaturesActivities.FeaturesActivity
import com.aurimteam.justhobby.R

class RegistryStartActivity: AppCompatActivity(), IRegistryStartView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry_start)

        val registry = findViewById<Button>(R.id.registryStartReadyBtn)
        registry.setOnClickListener { readyBtnClick() }
    }

    fun readyBtnClick() {
        var intent = Intent(this, FeaturesActivity::class.java)
        startActivity(intent)
    }
}