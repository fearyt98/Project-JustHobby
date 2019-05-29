package com.aurimteam.justhobby.SearchActivity.SearchFiltersFragment

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.SubcategoriesResponse
import kotlinx.android.synthetic.main.fragment_card_search_subcategories.*

class SearchFiltersFragment : BottomSheetDialogFragment(), ISearchFiltersView {

    private val adapter = SearchFiltersAdapter()
    private val presenter = SearchFiltersPresenter(this, SearchFiltersModel())
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(
            R.layout.fragment_card_search_subcategories,
            container, false
        )
        return view
    }

    override fun showFilters(subcategories: List<SubcategoriesResponse>) {
        adapter.onDataChange(subcategories)
    }

    override fun onStart() {
        super.onStart()
        presenter.getFilters()
        subcategoriesRecyclerView.layoutManager = LinearLayoutManager(context)
        subcategoriesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}