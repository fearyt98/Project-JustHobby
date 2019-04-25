package com.aurimteam.justhobby.AuthActivity

interface IAuthModel {
    fun checkUserData(loginMain: String, password: String, onFinishedListener: AuthModel.OnFinishedListener)
}