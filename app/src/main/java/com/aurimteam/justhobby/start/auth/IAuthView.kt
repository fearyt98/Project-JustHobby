package com.aurimteam.justhobby.start.auth

interface IAuthView {
    fun openMain()
    fun showMessage(message: String)
    fun loginUser()
    fun togglePB(isVisiblePB: Boolean)
}