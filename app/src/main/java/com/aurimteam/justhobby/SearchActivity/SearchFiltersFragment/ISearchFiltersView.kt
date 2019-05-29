package com.aurimteam.justhobby.SearchActivity.SearchFiltersFragment

import com.aurimteam.justhobby.Response.SubcategoriesResponse

interface ISearchFiltersView {
    fun showFilters(subcategories: List<SubcategoriesResponse>)
}