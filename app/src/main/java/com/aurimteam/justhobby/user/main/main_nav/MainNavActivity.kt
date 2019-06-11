package com.aurimteam.justhobby.user.main.main_nav

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_main_nav.*
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.aurimteam.justhobby.GpsData
import com.aurimteam.justhobby.NotificationsService
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.UserResponse
import com.aurimteam.justhobby.start.auth.AuthActivity
import com.aurimteam.justhobby.user.main.home.home.HomeTimelineFragment
import com.aurimteam.justhobby.user.main.notifications.NotificationsFragment
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.RecommendationFragment
import com.aurimteam.justhobby.user.main.settings.settings.SettingsFragment
import com.aurimteam.justhobby.user.search.search.SearchFragment

class MainNavActivity : AppCompatActivity(), IMainNavView {

    private val presenter = MainNavPresenter(this, MainNavModel(), this)
    val gpsData = GpsData()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(HomeTimelineFragment())
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

        val isGps = Settings(this).getPropertyBoolean("gps", true)
        if (isGps != null && isGps)
            gpsData.create(this, this)

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
                loadFragment(HomeTimelineFragment())
        } else {
            startNotifyService()
            loadFragment(HomeTimelineFragment())
        }

    }

    override fun onStart() {
        super.onStart()
        if (presenter.isSetViewContext())
            presenter.attachViewContext(this, this)

        presenter.checkToken()
    }

    override fun onStop() {
        super.onStop()
        presenter.onDestroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val fragment = supportFragmentManager.findFragmentById(R.id.mainNavContainerFragment)
        if (fragment != null)
            supportFragmentManager.putFragment(outState, "mainFragment", fragment)
    }

    override fun setGps(user: UserResponse) {
        if (user.attributes.address == null) {
            Settings(this).setPropertyBoolean("gps", true)
            if (!gpsData.isCreated)
                gpsData.create(this, this)
        } else {
            Settings(this).setPropertyBoolean("gps", false)
        }
    }

    override fun openAuth() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
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

    override fun onBackPressed() {
        val count = supportFragmentManager.findFragmentById(R.id.mainNavContainerFragment)

        if (count is SearchFragment) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainNavContainerFragment, RecommendationFragment())
                .commit()
        } else {
            super.onBackPressed()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            10 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    gpsData.configureGpsData()
                else Toast.makeText(this, "Разрешения не выданы", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun startNotifyService() {
        val mute = Settings(this).getPropertyBoolean("mute", false)
        val token = Settings(this).getProperty("token")
        if (mute != true) {
            val serviceIntent = Intent(this, NotificationsService::class.java)
            serviceIntent.putExtra("token", token)
            this.startService(serviceIntent)
        }
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
