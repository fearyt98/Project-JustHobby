package com.aurimteam.justhobby.user.search.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.widget.Space
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
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
import android.text.TextWatcher



class SearchFragment : Fragment(), ISearchView {

    private var presenter: SearchPresenter? = null
    private val adapter = SearchAdapter()
    private var filters: Bundle = Bundle()
    private var categories: Bundle = Bundle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        view.findViewById<ImageButton>(R.id.searchFilters).setOnClickListener { openFilters() }
        view.findViewById<ImageButton>(R.id.searchBtnBack).setOnClickListener { back() }
        presenter = SearchPresenter(this, SearchModel(), container?.context)

        if(savedInstanceState != null) {
            filters = savedInstanceState.getBundle("filters")!!
            categories = savedInstanceState.getBundle("categories")!!
        }
        return view
    }

    override fun setCategories(categories: List<CategoryResponse>) {
        adapter.onDataChange(categories)
    }

    override fun onStart() {
        super.onStart()
        if (presenter != null)
            if (!presenter!!.isSetView())
                presenter?.attachView(this)

        presenter?.getCategories()

        searchCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        searchCategories.adapter = adapter
        init()

        if(filters.size() == 0) {
            filters.putInt("costMaxAll", 15000)
            filters.putInt("costMinAll", 0)
            filters.putInt("ageMaxAll", 100)
            filters.putInt("ageMinAll", 0)
            filters.putBoolean("sortNear", false)
            filters.putBoolean("sortRating", true)
            filters.putInt("sortPrice", 0)
            filters.putBoolean("sexAny", true)
            filters.putBoolean("sexMan", false)
            filters.putBoolean("sexWoman", false)
            filters.putString("filterDays", "0,1,2,3,4,5,6")
            filters.putBoolean("statusAny", true)
            filters.putBoolean("statusTrue", false)
            filters.putBoolean("statusFalse", false)
            filters.putInt("ageMax", filters.getInt("ageMaxAll"))
            filters.putInt("ageMin", filters.getInt("ageMinAll"))
            filters.putInt("costMax", filters.getInt("costMaxAll"))
            filters.putInt("costMin", filters.getInt("costMinAll"))
        }

        val bundle = Bundle()
        bundle.putBundle("filters", filters)
        bundle.putBundle("categories", categories)
        bundle.putString("query", searchEditText.text.toString())

        val searchResultFragment = SearchResultFragment()
        searchResultFragment.arguments = bundle

        fragmentManager!!
            .beginTransaction()
            .replace(R.id.searchContainer, searchResultFragment)
            .commit()

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val resultFragment = fragmentManager!!.findFragmentById(R.id.searchContainer) as SearchResultFragment
                resultFragment.setQuery(s.toString())
            }
        })

        adapter.changeChecked(categories)
    }

    override fun onStop() {
        super.onStop()
        presenter?.detachView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBundle("filters", filters)
        outState.putBundle("categories", categories)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    private fun openFilters() {
        val bundle = Bundle()

        if (!filters.isEmpty)
            bundle.putAll(filters)

        val searchFiltersFragment = SearchFiltersFragment()
        searchFiltersFragment.arguments = bundle
        searchFiltersFragment.show(fragmentManager, "filters")
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

    fun setFilters(filtersNew: Bundle) {
        filters = filtersNew
        val resultFragment = fragmentManager!!.findFragmentById(R.id.searchContainer) as SearchResultFragment
        resultFragment.setFilters(filters)
    }

    fun getCategories(): Bundle {
        return categories
    }

    fun setCategories(categoriesNew: Bundle) {
        categories = categoriesNew
        val resultFragment = fragmentManager!!.findFragmentById(R.id.searchContainer) as SearchResultFragment
        resultFragment.setCategories(categoriesNew)

        adapter.changeChecked(categories)
    }
}