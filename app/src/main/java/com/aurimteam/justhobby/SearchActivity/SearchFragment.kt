package com.aurimteam.justhobby.SearchActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.SearchActivity.SearchResultsActivity.SearchResultFragment
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), ISearchView {

    private val presenter = SearchPresenter(this, SearchModel())
    private val adapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun setCategories(categories: List<String>) {
        adapter.onDataChange(categories)
    }

    override fun onStart() {
        super.onStart()
        presenter.getCategories()
        searchCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        searchCategories.adapter = adapter

        fragmentManager!!
            .beginTransaction()
            .replace(R.id.searchContainer, SearchResultFragment())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}