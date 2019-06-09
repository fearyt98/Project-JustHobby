package com.aurimteam.justhobby.user.main.home.user_bookmarks

import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

interface IUserBookmarksView {
    fun showUserBookmarks(bookmarks: List<CourseResponseR>, included: IncludedResponse?)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun deletedUserBookmark(position: Int)
}