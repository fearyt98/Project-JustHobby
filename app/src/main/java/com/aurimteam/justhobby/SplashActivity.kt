package com.aurimteam.justhobby

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aurimteam.justhobby.start.auth.AuthActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}