package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_main_rec_pop.*

class MainRecPopActivity : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewPagerAdapter = RecPopViewPagerAdapter(fragmentManager!!)
        recPopViewPager.adapter = viewPagerAdapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main_rec_pop, container, false)
    }
}
