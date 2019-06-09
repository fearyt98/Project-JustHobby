package com.aurimteam.justhobby.start.registry.registry

interface IRegistryView {
    fun togglePB(isVisiblePB: Boolean)
    fun openRegistryStart()
    fun showServerMessage(message: String)
    fun clearPasswordError(message: String)
    fun clearEmailError(message: String)
    fun changeLengthPasswords(message: String)
    fun changeLengthEmail(message: String)
    fun passwordsNotSimilar(message: String)
    fun hideErrors()
}