package com.aurimteam.justhobby.AuthActivity

interface IAuthModel {
    fun checkUserData(onFinishedListener: AuthModel.OnFinishedListener)
}