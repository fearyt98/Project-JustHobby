package com.aurimteam.justhobby.user.search.search

import com.aurimteam.justhobby.response.CategoryResponse

interface ISearchView {
    fun setCategories(categories: List<CategoryResponse>)
}