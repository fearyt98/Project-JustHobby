package com.aurimteam.justhobby.SearchActivity.SearchFiltersFragment

import com.aurimteam.justhobby.Response.SubcategoriesResponse

class SearchFiltersPresenter(private var view: ISearchFiltersView?, private val model: ISearchFiltersModel?) :
    SearchFiltersModel.onFinishedListener {

    override fun onResultSuccess(subcategories: List<SubcategoriesResponse>) {
        view?.showFilters(subcategories)
    }

    override fun onResultFail() {

    }

    fun getFilters(){
        model?.getFiltersData(this)
    }

    fun onDestroy(){
        view = null
    }
}