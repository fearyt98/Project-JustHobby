package com.aurimteam.justhobby.user.main.home.user_bookmarks

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.CourseResponse

class UserBookmarksPresenter(private var view: IUserBookmarksView?, private val model: IUserBookmarksModel?) :
    UserBookmarksModel.OnFinishedListener {

    override fun onResultSuccess(bookmarks: List<CourseResponse>) {
        view?.showUserBookmarks(bookmarks)
    }

    override fun deletedUserBookmark(position: Int) {
        view?.deletedUserBookmarks(position)
    }

    override fun onResultFail(strError: String?) {

    }

    fun deleteUserBookmarks(context: Context, courseId: Long, position: Int) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.deleteUserBookmarkData(token, courseId, position, this)
    }

    fun getUserBookmarks(context: Context) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.getUserBookmarksData(token, this)
    }

    fun onDestroy() {
        view = null
    }
}