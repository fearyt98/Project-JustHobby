package com.aurimteam.justhobby.user.main.settings.user_profile

import android.view.View

interface IUserProfileView {
    fun setUserDefaultInfo(email: String, name: String, lastName: String, address: String?)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
}