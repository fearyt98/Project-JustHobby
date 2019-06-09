package com.aurimteam.justhobby.user.search.filters_bottom_sheet

class SearchFiltersPresenter(private var view: ISearchFiltersView?, private val model: ISearchFiltersModel?) :
    SearchFiltersModel.OnFinishedListener {

    override fun onResultSuccess() {

    }

    override fun onResultFail(strError: String?) {

    }

    fun sendChosenFilters(){
        model?.sendChosenFiltersData(this)
    }

    fun onDestroy(){
        view = null
    }
}