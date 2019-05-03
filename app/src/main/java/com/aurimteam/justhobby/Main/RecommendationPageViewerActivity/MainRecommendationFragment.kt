package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_main_recommendation.*
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager


class MainRecommendationFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.activity_main_recommendation, container, false)
        val recommendationViewPager = view.findViewById<ViewPager>(R.id.recPopViewPager)
        val viewPagerAdapter = RecommendationViewPagerAdapter(childFragmentManager)
        recommendationViewPager.adapter = viewPagerAdapter
        return view
    }
}
