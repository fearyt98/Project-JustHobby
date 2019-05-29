package com.aurimteam.justhobby.SearchActivity.SearchResultsActivity

import com.aurimteam.justhobby.Response.CourseResponse

class SearchResultPresenter(private var view: ISearchResultView?, private val model: ISearchResultModel?) :
    SearchResultModel.onFinishedListener {

    override fun onResultSuccess(foundedCourses: List<CourseResponse>) {
        view?.showSearchResults(foundedCourses)
    }

    override fun onResultSuccess(number: Int) {

    }

    override fun onResultFail() {

    }

    fun getSearchResults() {
        model?.getSearchResultsData(this)
    }

    fun onDestroy() {
        view = null
    }
}