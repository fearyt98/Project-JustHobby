package com.aurimteam.justhobby.user.search.results

import android.content.Context
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
import com.aurimteam.justhobby.user.main.main_nav.MainNavActivity
import kotlinx.android.synthetic.main.fragment_search_results.*

class SearchResultFragment : Fragment(), ISearchResultView {

    private val presenter = SearchResultPresenter(this, SearchResultModel())
    private val adapter = CourseAdapter(presenter)
    private var filters: Bundle = Bundle()
    private var categories: Bundle = Bundle()
    private var query: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_results, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (arguments != null) {
            filters = arguments!!.getBundle("filters")!!
            categories = arguments!!.getBundle("categories")!!
            query = arguments!!.getString("query")!!
        }

        if (context != null)
            updateResults(context!!)

        searchResultsRecyclerView.layoutManager = LinearLayoutManager(context)
        searchResultsRecyclerView.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(searchResultsRecyclerView, false)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showSearchResults(foundedCourses: List<CourseResponseR>, included: IncludedResponse?) {
        if (foundedCourses.isEmpty() || included == null) {
            searchResultsProgressBar.visibility = View.GONE
            searchResultsParentRecyclerView.visibility = View.GONE
            searchResultsClear.visibility = View.VISIBLE
        } else {
            searchResultsClear.visibility = View.GONE
            toggleContentPB(false)
            adapter.onDataChange(foundedCourses, included)
        }
    }

    override fun deletedUserBookmark(position: Int) {
        adapter.deletedBookmark(position)
    }

    override fun addedUserBookmark(position: Int) {
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

    private fun updateResults(context: Context) {
        searchResultsClear.visibility = View.GONE
        var categoriesStr = ""
        val cats = categories.getIntegerArrayList("categories")
        if (cats != null && cats.isNotEmpty()) {
            var subcats = categories.getIntegerArrayList("category" + cats[0].toString())
            if (subcats != null)
                categoriesStr += subcats.joinToString(",")
            for (cat in cats)
                if (cats.indexOf(cat) != 0) {
                    subcats = categories.getIntegerArrayList("category$cat")
                    if (subcats != null && subcats.isNotEmpty())
                        categoriesStr += "," + subcats.joinToString(",")
                }
        }
        val sortPrice = filters.getInt("sortPrice")
        val maxPrice = filters.getInt("costMax", -1)
        val days = (filters.getString("filterDays") ?: "").split(',')

        if (activity != null) {
            val gpsData = (activity as MainNavActivity).gpsData
            presenter.getSearchResults(
                if (categoriesStr == "") null else categoriesStr,
                if (sortPrice == 0) null else sortPrice - 1,
                if (!filters.getBoolean("sortRating")) null else 1,
                if (!filters.getBoolean("sortNear")) null else 1,
                if (maxPrice == -1) null else maxPrice,
                filters.getInt("costMin", 0),
                filters.getInt("ageMax", 100),
                filters.getInt("ageMin", 0),
                if (!filters.getBoolean("sexAny")) null else 0,
                if (!filters.getBoolean("sexMale")) null else 1,
                if (!filters.getBoolean("sexFemale")) null else 2,
                if (days.contains("0")) 0 else null,
                if (days.contains("1")) 1 else null,
                if (days.contains("2")) 2 else null,
                if (days.contains("3")) 3 else null,
                if (days.contains("4")) 4 else null,
                if (days.contains("5")) 5 else null,
                if (days.contains("5")) 6 else null,
                if (filters.getBoolean("statusAny")) null else {
                    if (filters.getBoolean("statusTrue")) 1 else 0
                },
                if (query.length >= 3) query else null,
                gpsData.returnLat(),
                gpsData.returnLon(),
                context
            )
        }

    }

    fun setQuery(queryNew: String) {
        query = queryNew
        if (context != null && query.isNotEmpty())
            updateResults(context!!)
    }

    fun setFilters(filtersNew: Bundle) {
        filters = filtersNew
        if (context != null)
            updateResults(context!!)
    }

    fun setCategories(categoriesNew: Bundle) {
        categories = categoriesNew
        if (context != null)
            updateResults(context!!)
    }
}