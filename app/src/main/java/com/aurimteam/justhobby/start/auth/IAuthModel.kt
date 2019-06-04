package com.aurimteam.justhobby.start.auth

interface IAuthModel {
    fun loginUser(login: String, password: String, onFinishedListener: AuthModel.OnFinishedListener)
}