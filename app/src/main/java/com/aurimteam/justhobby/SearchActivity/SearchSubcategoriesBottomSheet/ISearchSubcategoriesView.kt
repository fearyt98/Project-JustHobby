package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesBottomSheet

import com.aurimteam.justhobby.Response.SubcategoriesResponse

interface ISearchSubcategoriesView {
    fun showSubcategories(subcategories: List<SubcategoriesResponse>)
}