package com.aurimteam.justhobby.user.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_main_nav.*
import android.support.v4.app.Fragment
import com.aurimteam.justhobby.user.main.home.home.HomeTimeLineFragment
import com.aurimteam.justhobby.user.main.notifications.NotificationsFragment
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.RecommendationFragment
import com.aurimteam.justhobby.user.main.settings.SettingsFragment

class MainNav : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(HomeTimeLineFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                loadFragment(RecommendationFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                loadFragment(NotificationsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu -> {
                loadFragment(SettingsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)
        mainNavNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState != null) {
            val fragment = supportFragmentManager.getFragment(savedInstanceState, "mainFragment")
            if (fragment != null)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainNavContainerFragment, fragment)
                    .commit()
            else
                loadFragment(HomeTimeLineFragment())
        } else
            loadFragment(HomeTimeLineFragment())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val fragment = supportFragmentManager.findFragmentById(R.id.mainNavContainerFragment)
        if (fragment != null)
            supportFragmentManager.putFragment(outState, "mainFragment", fragment)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        //смена фрагмента
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainNavContainerFragment, fragment)
                .commit()
            return true
        }
        return false
    }

}
