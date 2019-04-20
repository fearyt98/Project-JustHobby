package com.aurimteam.justhobby.HomeMainActivity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_home_main.*

class HomeMainActivity : AppCompatActivity(), IView {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
