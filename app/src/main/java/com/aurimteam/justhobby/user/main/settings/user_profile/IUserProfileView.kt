package com.aurimteam.justhobby.user.main.settings.user_profile

import com.aurimteam.justhobby.response.SuggestResponse

interface IUserProfileView {
    fun setUserDefaultInfo(email: String, name: String, lastName: String, address: String?)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun setSuggests(data: List<SuggestResponse>)
    fun userInfoSended()
}