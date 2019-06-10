package com.aurimteam.justhobby.user.main.settings.user_profile

interface IUserProfileModel {
    fun getUserInfoData(token: String, onFinishedListener: UserProfileModel.OnFinishedListener)
    fun sendUserInfoData(
        token: String, first_name: String,
        last_name: String,
        password_old: String,
        password: String,
        password_confirmation: String,
        address: String, onFinishedListener: UserProfileModel.OnFinishedListener)
    fun sendUserImage(token: String, filePath: String?, onFinishedListener: UserProfileModel.OnFinishedListener)
}