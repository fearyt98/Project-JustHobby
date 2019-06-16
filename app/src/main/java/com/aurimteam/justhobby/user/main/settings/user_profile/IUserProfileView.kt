package com.aurimteam.justhobby.user.main.settings.user_profile

import com.aurimteam.justhobby.response.SuggestResponse

interface IUserProfileView {
    fun setUserDefaultInfo(email: String, name: String, lastName: String, address: String?, image: String?)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun setSuggests(data: List<SuggestResponse>)
    fun userInfoSended()
    fun errorPasswordOld(message: String)
    fun errorPasswordNew(message: String)
    fun errorConfirmPass(message: String)
    fun errorFirstName(message: String)
    fun errorLastName(message: String)
    fun hidePasswordError()
    fun hideOtherError()
    fun passwordsSuccess()
    fun userNameClear(message: String)
    fun userLastNameClear(message: String)
}