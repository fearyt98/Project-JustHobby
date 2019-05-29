package com.aurimteam.justhobby.Start.AuthActivity

interface IAuthView {
    fun openMain()
    fun validEnter()
    fun setDataError(message: String)
    fun loginUser()
}