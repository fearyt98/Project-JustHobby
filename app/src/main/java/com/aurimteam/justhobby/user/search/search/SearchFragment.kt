package com.aurimteam.justhobby.user.search.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.widget.Space
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.user.search.results.SearchResultFragment
import kotlinx.android.synthetic.main.fragment_search.*
import android.view.inputmethod.InputMethodManager
import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.RecommendationFragment
import com.aurimteam.justhobby.user.search.filters_bottom_sheet.SearchFiltersFragment
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

class SearchFragment : Fragment(), ISearchView {

    private var presenter: SearchPresenter? = null
    private val adapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        view.findViewById<ImageButton>(R.id.searchFilters).setOnClickListener { openFilters() }
        view.findViewById<ImageButton>(R.id.searchBtnBack).setOnClickListener { back() }

        presenter = SearchPresenter(
            this,
            SearchModel(),
            container?.context
        )
        return view
    }

    override fun setCategories(categories: List<CategoryResponse>) {
        adapter.onDataChange(categories)
    }

    override fun onStart() {
        super.onStart()
        presenter?.getCategories()
        searchCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        searchCategories.adapter = adapter
        init()

        fragmentManager!!
            .beginTransaction()
            .replace(R.id.searchContainer, SearchResultFragment())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    private fun openFilters(){
        SearchFiltersFragment().show(fragmentManager,"Filters")
    }

    private fun init() {
        searchEditText.requestFocus()
        val imm =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.toggleSoftInput(0, 0)
        KeyboardVisibilityEvent.setEventListener(activity) { isOpen ->
            if (isOpen) {
                activity?.findViewById<BottomNavigationView>(R.id.mainNavNavigation)?.visibility = View.GONE
                activity?.findViewById<Space>(R.id.mainNavMarginSpacer)?.visibility = View.GONE
            } else {
                activity?.findViewById<BottomNavigationView>(R.id.mainNavNavigation)?.visibility = View.VISIBLE
                activity?.findViewById<Space>(R.id.mainNavMarginSpacer)?.visibility = View.VISIBLE
            }
        }
        search.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (hasFocus) {
                val inputMethodManager =
                    activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
                inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    private fun back() {
        fragmentManager!!
            .beginTransaction()
            .replace(R.id.mainNavContainerFragment, RecommendationFragment())
            .commit()
    }
}