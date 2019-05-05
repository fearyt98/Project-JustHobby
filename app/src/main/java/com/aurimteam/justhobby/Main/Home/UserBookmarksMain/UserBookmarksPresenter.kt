package com.aurimteam.justhobby.Main.Home.UserBookmarksMain

import com.aurimteam.justhobby.Response.CourseResponse

class UserBookmarksPresenter(private var view: IUserBookmarksView?, private val model: IUserBookmarksModel?) :
    UserBookmarksModel.OnFinishedListener {

    override fun onResultSuccess(bookmarks: List<CourseResponse>) {
        view?.showUserBookmarks(bookmarks)
    }

    override fun onResultFail() {

    }

    fun getUserBookmarks() {
        model?.getUserBookmarksData(this)
    }

    fun onDestroy() {
        view = null
    }
}