package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesBottomSheet

import com.aurimteam.justhobby.Response.SubcategoryResponse

interface ISearchSubcategoriesView {
    fun showSubcategories(subcategories: List<SubcategoryResponse>)
}