package com.aurimteam.justhobby.start.auth

interface IAuthView {
    fun openMain()
    fun openStartRegistry()
    fun showMessage(message: String)
    fun loginUser()
    fun togglePB(isVisiblePB: Boolean)
}