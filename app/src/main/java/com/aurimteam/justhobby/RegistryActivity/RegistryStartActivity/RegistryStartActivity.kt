package com.aurimteam.justhobby.RegistryActivity.RegistryStartActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aurimteam.justhobby.R

class RegistryStartActivity: AppCompatActivity(), IRegistryStartView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry_start)
    }
}