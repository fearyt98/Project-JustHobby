package com.aurimteam.justhobby.start.auth

import android.content.Context
import com.aurimteam.justhobby.R
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
        Settings(context!!).setPropertyBoolean("mute",  false)
        Settings(context!!).setPropertyBoolean("is_full_reg", token.is_full_reg)
        if (token.is_full_reg) view?.openMain()
        else view?.openStartRegistry()
    }

    override fun onResultFail(strError: String) {
        view?.togglePB(false)
        view?.showMessage(strError)
    }

    override fun wrongEmailOrPassword(strError: String) {
        view?.togglePB(false)
        if (strError == "The given data was invalid")
            view?.emailOrPasswordError(context!!.getString(R.string.wrong_data))
        else view?.showMessage(strError)
    }

    fun loginUser(loginMain: String, password: String) {
        if (loginMain == "" || password == "") {
            view?.hideErrors()
            if (loginMain == "")
                view?.clearEmailError(context!!.getString(R.string.empty_field))
            if (password == "")
                view?.clearPasswordError(context!!.getString(R.string.empty_field))
        } else if ((loginMain.length !in 5..256) || password.length !in 6..256) {
            view?.hideErrors()
            if (loginMain.length < 5)
                view?.changeLengthEmail(context!!.getString(R.string.min_5_symbols))
            if (loginMain.length > 255)
                view?.changeLengthEmail(context!!.getString(R.string.min_255_symbols))
            if (password.length < 6)
                view?.changeLengthPassword(context!!.getString(R.string.min_6_symbols))
            if (password.length > 255)
                view?.changeLengthPassword(context!!.getString(R.string.min_255_symbols))
        } else if (Settings(context!!).getProperty("token") == null) {
            view?.hideErrors()
            view?.togglePB(true)
            model?.loginUser(loginMain, password, this)
        } else view?.openMain()
    }

    fun isSetToken() {
        if (Settings(context!!).getProperty("token") != null)
            if (Settings(context!!).getPropertyBoolean("is_full_reg", true) != false)
                view?.openMain()
            else
                view?.openStartRegistry()
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachViewContext(view: IAuthView?, context: Context) {
        this.context = context
        this.view = view
    }

    fun onDestroy() {
        context = null
        view = null
    }
}