package com.aurimteam.justhobby.start.auth

interface IAuthView {
    fun openMain()
    fun validEnter()
    fun showMessage(message: String)
    fun loginUser()
}