package com.aurimteam.justhobby.SearchActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.fragment_recommendation_search.*

class SearchFragment : Fragment(), ISearchView {

    private val presenter = SearchPresenter(this, SearchModel())
    private val adapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recommendation_search, container, false)
        return view
    }

    override fun setCategories(categories: List<String>) {
        adapter.onDataChange(categories)
    }
    override fun onStart() {
        super.onStart()
        presenter.getCategories()
        recommendationSearchCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recommendationSearchCategories.adapter = adapter
    }



    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}