package com.aurimteam.justhobby.user.main.home.user_bookmarks

import com.aurimteam.justhobby.response.CourseResponse

interface IUserBookmarksView {
    fun showUserBookmarks(bookmarks: List<CourseResponse>)
}