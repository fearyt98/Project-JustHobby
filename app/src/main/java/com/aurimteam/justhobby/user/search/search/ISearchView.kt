package com.aurimteam.justhobby.user.search.search

import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.response.PriceRangeResponse

interface ISearchView {
    fun setCategories(categories: List<CategoryResponse>)
    fun setPriceRange(priceRange: PriceRangeResponse)
    fun showMessage(message: String?)
}