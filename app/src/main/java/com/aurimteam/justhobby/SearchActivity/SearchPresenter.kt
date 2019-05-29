package com.aurimteam.justhobby.SearchActivity

class SearchPresenter(private var view: ISearchView?, private val model: ISearchModel?) :
    SearchModel.onFinishedListener {

    override fun onResultSuccess(categories: List<String>) {
        view?.setCategories(categories)
    }

    override fun onResultSuccess(number: Int) {

    }

    override fun onResultFail() {

    }

    fun getCategories() {
        model?.getCategoriesData(this)
    }

    fun onDestroy() {
        view = null
    }
}