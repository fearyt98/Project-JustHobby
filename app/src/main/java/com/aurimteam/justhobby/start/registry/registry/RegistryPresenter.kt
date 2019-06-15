package com.aurimteam.justhobby.start.registry.registry

import android.content.Context
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.TokenResponse

class RegistryPresenter(
    private var view: IRegistryView?,
    private val model: IRegistryModel?,
    private var context: Context?
) : RegistryModel.OnFinishedListener {

    override fun onResultSuccess(data: TokenResponse) {
        if (data.token != "") {
            Settings(context!!).setProperty("token", data.token)
            Settings(context!!).setProperty("user_id", data.user_id.toString())
            if (data.is_full_reg != null)
                Settings(context!!).setPropertyBoolean("is_full_reg", data.is_full_reg)
        }
        view?.openRegistryStart()
    }

    override fun onResultFail(error: String) {
        view?.togglePB(false)
        if (error == "Unique")  view?.setErrorEmail("E-mail зарегестрирован")
        else if (error == "IncorrectEmail") view?.setErrorEmail("Некорректный ввод")
        else if (error == "IncorrectPass") view?.setErrorPassword("Некорректный ввод")
        else view?.showMessage(error)
    }

    fun sendUserInfo(email: String, password: String, confirmPassword: String) {
        if (email == "" || password == "" || confirmPassword == "") {
            view?.hideErrors()
            if (email == "")
                view?.setErrorEmail(context!!.getString(R.string.emptyField))
            if (password == "")
                view?.setErrorPassword(context!!.getString(R.string.emptyField))
            if (confirmPassword == "")
                view?.setErrorPasswordConfirm(context!!.getString(R.string.passwords_not_similar))
        } else if ((email.length !in 5..256) || password.length !in 6..256 || confirmPassword.length !in 6..256) {
            view?.hideErrors()
            if (email.length < 5)
                view?.setErrorEmail(context!!.getString(R.string.min_5_symbols))
            if (email.length > 255)
                view?.setErrorEmail(context!!.getString(R.string.min_255_symbols))
            if (password.length < 6 || confirmPassword.length < 6) {
                if (password.length < 6)
                    view?.setErrorPassword(context!!.getString(R.string.min_6_symbols))
                if (confirmPassword.length < 6)
                    view?.setErrorPasswordConfirm(context!!.getString(R.string.min_6_symbols))
            }
            if (password.length > 255 || confirmPassword.length > 255) {
                if (password.length > 255)
                    view?.setErrorPassword(context!!.getString(R.string.min_255_symbols))
                if (confirmPassword.length > 255)
                    view?.setErrorPasswordConfirm(context!!.getString(R.string.min_255_symbols))
            }
        } else if (password != confirmPassword) {
            view?.hideErrors()
            view?.setErrorPassword(context!!.getString(R.string.passwords_not_similar))
        } else {
            view?.hideErrors()
            view?.togglePB(true)
            model?.sendUserInfoData(email, password, confirmPassword, this)
        }
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachViewContext(view: IRegistryView?, context: Context) {
        this.context = context
        this.view = view
    }

    fun onDestroy() {
        context = null
        view = null
    }
}