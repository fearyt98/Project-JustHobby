package com.aurimteam.justhobby.user.main.main_nav

import com.aurimteam.justhobby.response.UserResponse

interface IMainNavView {
    fun openAuth()
    fun showMessage(message: String?)
    fun setGps(user: UserResponse)
}