package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.PopularCourses.PopularCoursesFragment

class RecommendationViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val COUNT_FRAGMENTS = 2
    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment =
                PopularCoursesFragment()
            1 -> fragment =
                PopularCoursesFragment()
        }
        return fragment
    }

    override fun getCount(): Int = COUNT_FRAGMENTS
    override fun getPageTitle(position: Int): CharSequence? {
        if (position == 0)
            return "ПОПУЛЯРНОЕ"
        else return "РЯДОМ"
    }
}