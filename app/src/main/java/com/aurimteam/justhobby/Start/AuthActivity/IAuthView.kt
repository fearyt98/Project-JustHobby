package com.aurimteam.justhobby.Start.AuthActivity

interface IAuthView {
    fun openMain()
    fun validEnter()
    fun showMessage(message: String)
    fun loginUser()
}