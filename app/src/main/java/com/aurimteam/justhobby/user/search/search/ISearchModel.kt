package com.aurimteam.justhobby.user.search.search

interface ISearchModel {

    fun getCategoriesData(Token: String, onFinishedListener: SearchModel.OnFinishedListener)
}