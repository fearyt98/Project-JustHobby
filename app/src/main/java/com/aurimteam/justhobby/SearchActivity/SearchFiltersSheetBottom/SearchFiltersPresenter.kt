package com.aurimteam.justhobby.SearchActivity.SearchFiltersSheetBottom

class SearchFiltersPresenter(private var view: ISearchFiltersView?, private val model: ISearchFiltersModel?) :
    SearchFiltersModel.onFinishedListener {

    override fun onResultSuccess() {

    }

    override fun onResultFail() {

    }

    fun sendChosenFilters(){
        model?.sendChosenFiltersData(this)
    }

    fun onDestroy(){
        view = null
    }
}