package com.aurimteam.justhobby.SearchActivity.SearchFiltersFragment

import com.aurimteam.justhobby.Response.SubcategoriesResponse

class SearchFiltersModel : ISearchFiltersModel{
    interface onFinishedListener{
        fun onResultSuccess(subcategories: List<SubcategoriesResponse>)
        fun onResultFail()
    }

    override fun getFiltersData(onFinishedListener: onFinishedListener) {

    }
}