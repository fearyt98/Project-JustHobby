package com.aurimteam.justhobby.user.search.filters_bottom_sheet

class SearchFiltersModel: ISearchFiltersModel {
    interface OnFinishedListener{
        fun onResultSuccess()
        fun onResultFail()
    }

    override fun sendChosenFiltersData(onFinishedListener: OnFinishedListener) {

    }
}