package com.aurimteam.justhobby.Main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_main_nav.*
import android.support.v4.app.Fragment
import com.aurimteam.justhobby.Main.Home.HomeTimeLineClearFragment
import com.aurimteam.justhobby.Main.Home.HomeTimeLineFragment
import com.aurimteam.justhobby.Main.Home.UserBookmarksMain.UserBookmarksClearFragment
import com.aurimteam.justhobby.Main.Home.UserCourses.UserCoursesClearFragment
import com.aurimteam.justhobby.Main.Notifications.NotificationsFragment
import com.aurimteam.justhobby.Main.RecommendationPageViewer.RecommendationFragment
import com.aurimteam.justhobby.Main.Settings.SettingsFragment


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
    }

    override fun onResume() {
        super.onResume()
        loadFragment(HomeTimeLineFragment())
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
