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
        view?.showServerMessage(strError)
    }

    override fun wrongEmailOrPassword(strError: String) {
        if (strError == "The given data was invalid")
            view?.togglePB(false)
        view?.emailOrPasswordError("Неверные данные")
    }

    fun loginUser(loginMain: String, password: String) {
        if (loginMain == "" || password == "") {
            view?.hideErrors()
            if (loginMain == "")
                view?.clearEmailError("Пустое поле «E-mail»")
            if (password == "")
                view?.clearPasswordError("Пустое поле «Пароль»")
        } else if ((loginMain.length !in 4..256) || password.length !in 4..256) {
            view?.hideErrors()
            if (loginMain.length < 5 || loginMain.length > 255)
                view?.changeLengthEmail("Мин. 5 и максимум 255 символов")
            if (password.length < 5 || password.length > 255)
                view?.changeLengthPassword("Мин. 5 и максимум 255 символов")
        } else if (Settings(context!!).getProperty("token") == null) {
            view?.hideErrors()
            view?.togglePB(true)
            model?.loginUser(loginMain, password, this)
        } else view?.openMain()
    }

    fun isSetToken() {
        if (Settings(context!!).getProperty("token") != null &&
            Settings(context!!).getPropertyBoolean("is_full_reg", false) != false
        ) view?.openMain()
        else if (Settings(context!!).getProperty("token") != null
            && Settings(context!!).getPropertyBoolean("is_full_reg", false) == false
        ) view?.openStartRegistry()
    }

    fun onDestroy() {
        context = null
        view = null
    }
}