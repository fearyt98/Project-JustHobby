package com.aurimteam.justhobby.Main.Home.UserBookmarksMain

import com.aurimteam.justhobby.Response.CourseResponse

interface IUserBookmarksView {
    fun showUserBookmarks(bookmarks: List<CourseResponse>)
}