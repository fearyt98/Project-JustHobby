package com.aurimteam.justhobby.user.main.home.user_bookmarks

interface IUserBookmarksModel {
    fun getUserBookmarksData(OnFinishedListener: UserBookmarksModel.OnFinishedListener)
    fun deleteUserBookmarkData(token: String, courseId: Long, position: Int, OnFinishedListener: UserBookmarksModel.OnFinishedListener)
}