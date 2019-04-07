package com.aurimteam.justhobby.RegistryActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.RecoverActivity.RecoverActivity

class Registry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)
    }
    fun regBtnClick(view: View){
        var intent = Intent(this, RecoverActivity::class.java)
        startActivity(intent)
    }
}
