package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_main_recommendation.*

class MainRecommendationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recommendation)
        val viewPagerAdapter = RecommendationViewPagerAdapter(supportFragmentManager)
        recPopViewPager.adapter = viewPagerAdapter
    }

    override fun onResume() {
        super.onResume()
        val recommendationView = findViewById<LinearLayout>(R.id.recommendation)
        recommendationView.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (hasFocus) {
                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}
