package com.aurimteam.justhobby.user.main.home.user_bookmarks

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

class UserBookmarksPresenter(private var view: IUserBookmarksView?, private val model: IUserBookmarksModel?) :
    UserBookmarksModel.OnFinishedListener {

    override fun onResultSuccess(bookmarks: List<CourseResponseR>, included: IncludedResponse?) {
        view?.showUserBookmarks(bookmarks, included)
    }

    override fun deletedUserBookmark(position: Int) {
        view?.deletedUserBookmark(position)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    fun deleteUserBookmarks(context: Context, courseId: Long, position: Int) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.deleteUserBookmark(token, courseId, position, this)
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