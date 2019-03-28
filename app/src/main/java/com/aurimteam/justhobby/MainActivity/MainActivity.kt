package com.aurimteam.justhobby.MainActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.RegistryActivity.Registry

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun clickEnter(view: View){
        //this в intent указывает на текущую активити
        var intent = Intent(this, Registry::class.java)
        startActivity(intent)
    }
}
