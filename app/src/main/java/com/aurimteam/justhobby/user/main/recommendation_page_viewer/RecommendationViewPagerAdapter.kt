package com.aurimteam.justhobby.user.main.recommendation_page_viewer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.near_user_courses.NearUserCoursesFragment
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses.PopularCoursesFragment

class RecommendationViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val COUNT_FRAGMENTS = 2
    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment =
                NearUserCoursesFragment()
            1 -> fragment =
                PopularCoursesFragment()
        }
        return fragment
    }

    override fun getCount(): Int = COUNT_FRAGMENTS
    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "РЯДОМ С ВАМИ" else "ПОПУЛЯРНОЕ"
    }
}