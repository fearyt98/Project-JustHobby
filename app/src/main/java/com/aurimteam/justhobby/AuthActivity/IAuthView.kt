package com.aurimteam.justhobby.AuthActivity

interface IAuthView {
    //fun setData()
    fun validEnter()
    fun setDataError()
    fun getUserData()
    fun getUserDataVK()
    fun getUserDataGoogle()
    fun getUserDataFB()
    fun forgetChangeActivity()
    fun registryChangeActivity()
}