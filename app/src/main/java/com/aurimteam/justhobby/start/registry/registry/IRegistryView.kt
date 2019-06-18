package com.aurimteam.justhobby.start.registry.registry

interface IRegistryView {
    fun togglePB(isVisiblePB: Boolean)
    fun openRegistryStart()
    fun showMessage(message: String?)
    fun hideErrors()
    fun setErrorEmail(message: String)
    fun setErrorPassword(message: String)
    fun setErrorPasswordConfirm(message: String)
}