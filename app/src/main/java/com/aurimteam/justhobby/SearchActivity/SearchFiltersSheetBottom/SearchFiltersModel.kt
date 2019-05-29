package com.aurimteam.justhobby.SearchActivity.SearchFiltersSheetBottom

class SearchFiltersModel: ISearchFiltersModel {
    interface onFinishedListener{
        fun onResultSuccess()
        fun onResultFail()
    }

    override fun sendChosenFiltersData(onFinishedListener: onFinishedListener) {

    }
}