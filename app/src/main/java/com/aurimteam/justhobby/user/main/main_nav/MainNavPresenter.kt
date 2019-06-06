package com.aurimteam.justhobby.user.main.main_nav

import android.content.Context
import com.aurimteam.justhobby.response.LoginResponse
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.UserResponse

class MainNavPresenter(private var view: IMainNavView?, private val model: IMainNavModel?, private var context: Context?) :
    MainNavModel.OnFinishedListener {

    override fun onResultFail(strError: String) {
        //view?.showMessage(strError)
        Settings(context!!).removeProperty("token")
        Settings(context!!).removeProperty("user_id")
        view?.openAuth()
    }

    fun checkToken() {
        val token = Settings(context!!).getProperty("token")
        if (token != null)
            model?.getUser(token, this)
        else
            view?.openAuth()
    }

    fun onDestroy() {
        view = null
    }
}