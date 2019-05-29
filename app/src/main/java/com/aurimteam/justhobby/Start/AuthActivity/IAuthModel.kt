package com.aurimteam.justhobby.Start.AuthActivity

interface IAuthModel {
    fun checkUserData(loginMain: String, password: String, onFinishedListener: AuthModel.OnFinishedListener)
}