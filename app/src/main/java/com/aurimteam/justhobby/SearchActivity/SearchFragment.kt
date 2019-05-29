package com.aurimteam.justhobby.SearchActivity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.widget.Space
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.SearchActivity.SearchResultsActivity.SearchResultFragment
import kotlinx.android.synthetic.main.fragment_search.*
import android.view.inputmethod.InputMethodManager
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener


class SearchFragment : Fragment(), ISearchView {

    private val presenter = SearchPresenter(this, SearchModel())
    private val adapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.aurimteam.justhobby.R.layout.fragment_search, container, false)
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
        searchEditText.requestFocus()
        val imm = this.activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
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