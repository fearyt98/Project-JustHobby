package com.aurimteam.justhobby.SearchActivity.SearchResultsActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R

class SearchResultClearFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(
            R.layout.fragment_recommendation_search_fr_no_results,
            container, false
        )
        return view
    }
}