package com.aurimteam.justhobby.SearchActivity

interface ISearchModel {

    fun getCategoriesData(Token: String, onFinishedListener: SearchModel.onFinishedListener)
}