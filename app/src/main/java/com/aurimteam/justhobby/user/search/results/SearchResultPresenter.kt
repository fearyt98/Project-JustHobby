package com.aurimteam.justhobby.user.search.results

import com.aurimteam.justhobby.response.CourseResponse

class SearchResultPresenter(private var view: ISearchResultView?, private val model: ISearchResultModel?) :
    SearchResultModel.OnFinishedListener {

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