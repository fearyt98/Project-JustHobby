package com.aurimteam.justhobby.user.main.settings.user_profile

import android.view.View

interface IUserProfileView {
    fun setUserDeafultInfo(email: String, name: String, lastName: String, address: String?)
    fun showServerMessage(message: String)
    fun toggleContentPB(isVisiblePB: Boolean)
}