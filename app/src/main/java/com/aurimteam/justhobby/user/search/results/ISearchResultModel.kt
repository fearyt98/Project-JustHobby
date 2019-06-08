package com.aurimteam.justhobby.user.search.results

interface ISearchResultModel{
    fun getSearchResultsData(token: String, OnFinishedListener: SearchResultModel.OnFinishedListener)
    fun deleteUserBookmark(token: String, courseId: Long, position: Int, OnFinishedListener: SearchResultModel.OnFinishedListener)
    fun addUserBookmark(token: String, courseId: Long, position: Int, OnFinishedListener: SearchResultModel.OnFinishedListener)
}