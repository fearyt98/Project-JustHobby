package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

import com.aurimteam.justhobby.response.SubcategoryResponse

interface ISearchSubcategoriesView {
    fun showSubcategories(subcategories: List<SubcategoryResponse>)
}