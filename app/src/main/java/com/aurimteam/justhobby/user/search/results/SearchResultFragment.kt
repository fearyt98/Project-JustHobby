package com.aurimteam.justhobby.user.search.results

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.course.CourseAdapter
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import kotlinx.android.synthetic.main.fragment_search_results.*

class SearchResultFragment : Fragment(), ISearchResultView {

    private val presenter = SearchResultPresenter(this, SearchResultModel())
    private val adapter = CourseAdapter(presenter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_results, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (context != null) presenter.getSearchResults(context!!)
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(context)
        searchResultsRecyclerView.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(searchResultsRecyclerView, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showSearchResults(foundedCourses: List<CourseResponseR>, included: IncludedResponse?) {
        if (foundedCourses.isEmpty() || included == null) {
            searchResultsProgressBar.visibility = View.GONE
            searchResultsParentRecyclerView.visibility = View.GONE
            searchResultsClear.visibility = View.VISIBLE
        } else {
            toggleContentPB(false)
            adapter.onDataChange(foundedCourses, included)
        }
    }

    override fun deletedUserBookmarks(position: Int) {
        adapter.deletedBookmark(position)
    }

    override fun addedUserBookmarks(position: Int) {
        adapter.addedBookmark(position)
    }

    override fun toggleContentPB(isVisiblePB: Boolean) {
        if (isVisiblePB) {
            searchResultsProgressBar.visibility = View.VISIBLE
            searchResultsParentRecyclerView.visibility = View.GONE
        } else {
            searchResultsProgressBar.visibility = View.GONE
            searchResultsParentRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }
}