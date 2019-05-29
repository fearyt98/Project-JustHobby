package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesFragment

import com.aurimteam.justhobby.Response.SubcategoryResponse

interface ISearchSubcategoriesView {
    fun showSubcategories(subcategories: List<SubcategoryResponse>)
}