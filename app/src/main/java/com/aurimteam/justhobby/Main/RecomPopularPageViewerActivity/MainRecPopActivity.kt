package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity

import android.os.Bundle
import android.support.v4.view.PagerTabStrip
import android.support.v7.app.AppCompatActivity
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_main_rec_pop.*

class MainRecPopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_rec_pop)
        val viewPagerAdapter = RecPopViewPagerAdapter(supportFragmentManager)
        recPopViewPager.adapter = viewPagerAdapter
    }
}
