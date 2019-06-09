package com.aurimteam.justhobby.user.main.main_nav

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_main_nav.*
import android.support.v4.app.Fragment
import android.view.Gravity
import android.widget.Toast
import com.aurimteam.justhobby.start.auth.AuthActivity
import com.aurimteam.justhobby.user.main.home.home.HomeTimelineFragment
import com.aurimteam.justhobby.user.main.notifications.NotificationsFragment
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.RecommendationFragment
import com.aurimteam.justhobby.user.main.settings.settings.SettingsFragment
import com.aurimteam.justhobby.user.search.search.SearchFragment

class MainNavActivity : AppCompatActivity(), IMainNavView {

    private val presenter = MainNavPresenter(this, MainNavModel(), this)

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
        } else
            loadFragment(HomeTimelineFragment())
    }

    override fun onStart() {
        super.onStart()

        presenter.checkToken()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val fragment = supportFragmentManager.findFragmentById(R.id.mainNavContainerFragment)
        if (fragment != null)
            supportFragmentManager.putFragment(outState, "mainFragment", fragment)
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
