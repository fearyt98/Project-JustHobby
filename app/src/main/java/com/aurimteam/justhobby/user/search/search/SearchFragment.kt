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
import android.view.Gravity
import android.widget.Toast
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.PriceRangeResponse

class SearchFragment : Fragment(), ISearchView {

    private var presenter: SearchPresenter = SearchPresenter(this, SearchModel())
    private val adapter = SearchAdapter()
    private var filters: Bundle = Bundle()
    private var categories: Bundle = Bundle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        view.findViewById<ImageButton>(R.id.searchFilters).setOnClickListener { openFilters() }
        view.findViewById<ImageButton>(R.id.searchBtnBack).setOnClickListener { back() }

        if (savedInstanceState != null) {
            filters = savedInstanceState.getBundle("filters")!!
            categories = savedInstanceState.getBundle("categories")!!
        }
        return view
    }

    override fun setCategories(categories: List<CategoryResponse>) {
        adapter.onDataChange(categories)
        adapter.changeChecked(this.categories)
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (context != null)
            presenter.getCategories(context!!)
        presenter.getPriceRange(context!!)

        searchCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        searchCategories.adapter = adapter

        if (filters.size() == 0) {
            init()
            filters.putInt("costMaxAll", 15000)
            filters.putInt("costMinAll", 0)
            filters.putInt("ageMaxAll", 100)
            filters.putInt("ageMinAll", 0)
            filters.putBoolean("sortNear", false)
            filters.putBoolean("sortRating", true)
            filters.putInt("sortPrice", 0)
            filters.putBoolean("sexAny", false)
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
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val resultFragment = fragmentManager!!.findFragmentById(R.id.searchContainer) as SearchResultFragment
                resultFragment.setQuery(s.toString())
            }
        })
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBundle("filters", filters)
        outState.putBundle("categories", categories)
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.findViewById<BottomNavigationView>(R.id.mainNavNavigation)?.visibility = View.VISIBLE
        activity?.findViewById<Space>(R.id.mainNavMarginSpacer)?.visibility = View.VISIBLE
        presenter.detachView()
    }

    override fun setPriceRange(priceRange: PriceRangeResponse) {
        filters.putInt("costMaxAll", priceRange.max_price)
        filters.putInt("costMinAll", priceRange.min_price)
    }

    override fun showMessage(message: String?) {
        val devMode = Settings(context!!).getPropertyBoolean("dev_mode", false)
        if (devMode != null && devMode) {
            val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 30)
            toast.show()
        }
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
        if(!equalBundles(filters, filtersNew)) {
            filters = filtersNew
            val resultFragment = fragmentManager!!.findFragmentById(R.id.searchContainer) as SearchResultFragment
            resultFragment.setFilters(filters)
        }
    }

    fun getCategories(): Bundle {
        return categories
    }

    fun setCategories(categoriesNew: Bundle) {
        if(!equalBundles(categories, categoriesNew)) {
            categories = categoriesNew
            val resultFragment = fragmentManager!!.findFragmentById(R.id.searchContainer) as SearchResultFragment
            resultFragment.setCategories(categoriesNew)

            adapter.changeChecked(categories)
        }
    }

    private fun equalBundles(one: Bundle, two: Bundle): Boolean {
        if (one.size() != two.size())
            return false

        val setOne = HashSet(one.keySet())
        setOne.addAll(two.keySet())
        var valueOne: Any?
        var valueTwo: Any?

        for (key in setOne) {
            if (!one.containsKey(key) || !two.containsKey(key))
                return false

            valueOne = one.get(key)
            valueTwo = two.get(key)
            if (valueOne is Bundle && valueTwo is Bundle &&
                !equalBundles(valueOne, valueTwo)
            ) {
                return false
            } else if (valueOne == null) {
                if (valueTwo != null)
                    return false
            } else if (valueOne != valueTwo)
                return false
        }

        return true
    }
}