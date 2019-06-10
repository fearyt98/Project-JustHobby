package com.aurimteam.justhobby.user.search.search

interface ISearchModel {

    fun getCategoriesData(token: String, onFinishedListener: SearchModel.OnFinishedListener)
    fun getPriceRangeData(token: String, onFinishedListener: SearchModel.OnFinishedListener)
}