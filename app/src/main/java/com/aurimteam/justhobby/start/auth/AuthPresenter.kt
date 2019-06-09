package com.aurimteam.justhobby.start.auth

import android.content.Context
import com.aurimteam.justhobby.response.TokenResponse
import com.aurimteam.justhobby.Settings

class AuthPresenter(private var view: IAuthView?, private val model: IAuthModel?, private var context: Context?) :
    AuthModel.OnFinishedListener {
    /*
    Present работает только с интерфейсом View (и методами интерфейса)
    Сами методы View не используются в Presenter
    Таким образом выполняется связь между Presenter и View
    */
    override fun onResultSuccess(token: TokenResponse) {
        Settings(context!!).setProperty("token", token.token)
        Settings(context!!).setProperty("user_id", token.user_id.toString())
        Settings(context!!).setPropertyBoolean("is_full_reg", token.is_full_reg)
        if (token.is_full_reg) view?.openMain()
        else view?.openStartRegistry()
    }

    override fun onResultFail(strError: String) {
        view?.togglePB(false)
        view?.showMessage(strError)
    }

    fun loginUser(loginMain: String, password: String) {
        if (Settings(context!!).getProperty("token") == null) {
            view?.togglePB(true)
            model?.loginUser(loginMain, password, this)
        } else view?.openMain()
    }

    fun onDestroy() {
        view = null
    }

    fun isSetToken() {
        if (Settings(context!!).getProperty("token") != null)
            if (Settings(context!!).getPropertyBoolean("is_full_reg", true) != false)
                view?.openMain()
            else
                view?.openStartRegistry()
    }
}