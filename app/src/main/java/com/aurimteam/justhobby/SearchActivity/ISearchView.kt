package com.aurimteam.justhobby.SearchActivity

import com.aurimteam.justhobby.Response.CategoryResponse

interface ISearchView {
    fun setCategories(categories: List<CategoryResponse>)
}