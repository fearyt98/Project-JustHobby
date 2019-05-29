package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesFragment

import com.aurimteam.justhobby.Response.SubcategoriesResponse

interface ISearchSubcategoriesView {
    fun showSubcategories(subcategories: List<SubcategoriesResponse>)
}