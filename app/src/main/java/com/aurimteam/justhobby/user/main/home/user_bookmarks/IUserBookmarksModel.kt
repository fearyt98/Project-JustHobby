package com.aurimteam.justhobby.user.main.home.user_bookmarks

interface IUserBookmarksModel {
    fun getUserBookmarksData(
        token: String,
        lat: Float?,
        lon: Float?,
        onFinishedListener: UserBookmarksModel.OnFinishedListener
    )

    fun deleteUserBookmark(
        token: String,
        courseId: Long,
        position: Int,
        onFinishedListener: UserBookmarksModel.OnFinishedListener
    )
}