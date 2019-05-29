package com.aurimteam.justhobby.SearchActivity.SearchResultsActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse
import kotlinx.android.synthetic.main.fragment_recommendation_search_fr_results.*

class SearchResultFragment : Fragment(), ISearchResultView {

    private val presenter = SearchResultPresenter(this, SearchResultModel())
    private val adapter = SearchResultAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(
            R.layout.fragment_recommendation_search_fr_results,
            container, false
        )
        return view
    }

    override fun showSearchResults(foundedCourses: List<CourseResponse>) {
        adapter.onDataChange(foundedCourses)
    }

    override fun onStart() {
        super.onStart()
        presenter.getSearchResults()
        searchResultsRecycler.layoutManager = LinearLayoutManager(context)
        searchResultsRecycler.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}