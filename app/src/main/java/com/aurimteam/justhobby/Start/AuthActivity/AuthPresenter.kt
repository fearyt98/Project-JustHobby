package com.aurimteam.justhobby.Start.AuthActivity

import com.aurimteam.justhobby.Response.AuthResponse

class AuthPresenter(private var view: IAuthView?, private val model: IAuthModel?) : AuthModel.OnFinishedListener {
    /*
    Present работает только с интерфейсом View (и методами интерфейса)
    Сами методы View не используются в Presenter
    Таким образом выполняется связь между Presenter и View
    */

    override fun onResultSuccess(token: AuthResponse) {
        view!!.validEnter()
        val dd = token.token
    }

    override fun onResultFail(strError: String) {
        val dd = strError
    }

    fun gettingUserData(loginMain: String, password: String) {
        model?.checkUserData(loginMain, password, this)

    }

    fun gettingUserDataVK() {

    }

    fun gettingUserDataFB() {

    }

    fun gettingUserDataGoogle() {

    }

    fun onDestroy() {
        view = null
    }
}