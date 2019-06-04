package com.aurimteam.justhobby.user.main.home.user_bookmarks

import com.aurimteam.justhobby.response.CourseResponse

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