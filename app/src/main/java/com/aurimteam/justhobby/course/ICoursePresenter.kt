package com.aurimteam.justhobby.course

import android.content.Context

interface ICoursePresenter {
    fun deleteUserBookmark(context: Context, courseId: Long, position: Int)
    fun addUserBookmark(context: Context, courseId: Long, position: Int)
}