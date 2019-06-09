package com.aurimteam.justhobby.group

import android.content.Context

interface IGroupPresenter {
    fun deleteUserGroup(context: Context, groupId: Long, position: Int)
    fun addUserGroup(context: Context, groupId: Long, position: Int)
}