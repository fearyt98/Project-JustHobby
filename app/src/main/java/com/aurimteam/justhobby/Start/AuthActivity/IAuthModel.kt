package com.aurimteam.justhobby.Start.AuthActivity

interface IAuthModel {
    fun loginUser(login: String, password: String, onFinishedListener: AuthModel.OnFinishedListener)
}