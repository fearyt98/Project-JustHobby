package com.aurimteam.justhobby.Main.RecommendationPageViewer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import android.support.v4.view.ViewPager

class MainRecommendationFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_main_recommendation, container, false)
        val recommendationViewPager = view.findViewById<ViewPager>(R.id.recPopViewPager)
        recommendationViewPager.adapter = RecommendationViewPagerAdapter(childFragmentManager)
        return view
    }
}
