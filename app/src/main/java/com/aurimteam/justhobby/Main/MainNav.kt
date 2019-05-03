package com.aurimteam.justhobby.Main

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_main_nav.*
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import com.aurimteam.justhobby.Main.HomeActivity.HomeTimeLineFragment
import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.MainRecommendationFragment
import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.PopularCourses.PopularCoursesFragment


class MainNav : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(HomeTimeLineFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                loadFragment(MainRecommendationFragment())
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
        setContentView(R.layout.activity_main_nav)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        loadFragment(HomeTimeLineFragment())
        /*val mainView = findViewById<LinearLayout>(R.id.container_home_main)
        mainView.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (hasFocus) {
                val inputMethodManager =
                    getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }*/
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        //смена фрагмента
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            return true
        }
        return false
    }

}
