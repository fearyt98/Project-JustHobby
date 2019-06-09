package com.aurimteam.justhobby.start.registry.start

interface IRegistryStartView {
    fun userRegistered()
    fun togglePB(isVisiblePB: Boolean)
    fun clearFirstName(message: String)
    fun clearLastName(message: String)
    fun changeLengthFirstName(message: String)
    fun changeLengthLastName(message: String)
    fun showServerMessage(message: String)
    fun hideErrors()
}