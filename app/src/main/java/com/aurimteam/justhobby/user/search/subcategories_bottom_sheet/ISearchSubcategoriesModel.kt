package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

interface ISearchSubcategoriesModel {
    fun getSubcategoriesData(token: String, categoryId: Int, onFinishedListener: SearchSubcategoriesModel.OnFinishedListener)
}