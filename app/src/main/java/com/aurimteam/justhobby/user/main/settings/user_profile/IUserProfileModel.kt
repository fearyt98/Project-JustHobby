package com.aurimteam.justhobby.user.main.settings.user_profile

import android.content.Context
import android.net.Uri

interface IUserProfileModel {
    fun getUserInfoData(token: String, onFinishedListener: UserProfileModel.OnFinishedListener)
    fun getSuggests(token: String, query: String, onFinishedListener: UserProfileModel.OnFinishedListener)
    fun sendUserInfoData(
        token: String,
        first_name: String,
        last_name: String,
        address: String?,
        onFinishedListener: UserProfileModel.OnFinishedListener
    )

    fun sendUserPassword(
        token: String,
        password_old: String?,
        password: String?,
        password_confirmation: String?,
        onFinishedListener: UserProfileModel.OnFinishedListener
    )

    fun sendUserImage(
        token: String,
        filePath: Uri,
        context: Context,
        onFinishedListener: UserProfileModel.OnFinishedListener
    )
}