package com.aurimteam.justhobby.start.recovery

interface IRecoveryView {
    fun togglePB(isVisiblePB: Boolean)
    fun recoveryEmail()
    fun backToMainActivity()
    fun hideError()
    fun emailError(message: String)
    fun clearEmailError(message: String)
    fun changeLengthEmail(message: String)
    fun showServerMessage(message: String)
}