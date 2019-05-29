package com.aurimteam.justhobby.Main.RecommendationPageViewer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import android.support.v4.view.ViewPager
import android.widget.EditText
import com.aurimteam.justhobby.SearchActivity.SearchFragment

class RecommendationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_recommendation, container, false)
        view.findViewById<EditText>(R.id.recommendationSearch)
            .setOnFocusChangeListener { editText: View, hasFocus: Boolean ->
                if (hasFocus) {
                    editText.isFocusable = false
                    openSearchFragment()
                }
            }
        val recommendationViewPager = view.findViewById<ViewPager>(R.id.recommendationViewPager)
        recommendationViewPager.adapter = RecommendationViewPagerAdapter(childFragmentManager)
        return view
    }

    private fun openSearchFragment() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, SearchFragment())
            .commit()
    }
}
