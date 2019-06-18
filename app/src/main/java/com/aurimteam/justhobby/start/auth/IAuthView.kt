package com.aurimteam.justhobby.start.auth

interface IAuthView {
    fun openMain()
    fun openStartRegistry()
    fun showMessage(message: String?)
    fun loginUser()
    fun togglePB(isVisiblePB: Boolean)
    fun clearPasswordError(message: String)
    fun clearEmailError(message: String)
    fun emailOrPasswordError(message: String)
    fun hideErrors()
    fun changeLengthPassword(message: String)
    fun changeLengthEmail(message: String)

}