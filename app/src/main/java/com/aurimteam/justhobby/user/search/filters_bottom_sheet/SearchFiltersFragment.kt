package com.aurimteam.justhobby.user.search.filters_bottom_sheet

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.aurimteam.justhobby.R

class SearchFiltersFragment : BottomSheetDialogFragment(), ISearchFiltersView {

    private val presenter = SearchFiltersPresenter(this, SearchFiltersModel())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_filters, container, false)
        view.findViewById<Button>(R.id.filtersAcceptButton).setOnClickListener { sendChosenFilters() }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun sendChosenFilters(){
        presenter.sendChosenFilters()
    }
}