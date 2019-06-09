package com.aurimteam.justhobby.user.search.results

interface ISearchResultModel{
    fun getSearchResultsData(token: String, onFinishedListener: SearchResultModel.OnFinishedListener)
    fun deleteUserBookmark(token: String, courseId: Long, position: Int, onFinishedListener: SearchResultModel.OnFinishedListener)
    fun addUserBookmark(token: String, courseId: Long, position: Int, onFinishedListener: SearchResultModel.OnFinishedListener)
}