package com.aurimteam.justhobby.start.recovery

interface IRecoveryView {
    fun togglePB(isVisiblePB: Boolean)
    fun recoveryEmail()
    fun back()
    fun hideError()
    fun emailError(message: String)
    fun clearEmailError(message: String)
    fun changeLengthEmail(message: String)
    fun showMessage(message: String?)
}