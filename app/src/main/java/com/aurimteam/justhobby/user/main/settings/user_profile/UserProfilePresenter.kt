package com.aurimteam.justhobby.user.main.settings.user_profile

import android.content.Context
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.SuggestResponse
import com.aurimteam.justhobby.response.UserResponse

class UserProfilePresenter(private var view: IUserProfileView?, private val model: IUserProfileModel?) :
    UserProfileModel.OnFinishedListener {

    override fun onResultSuccess(user: UserResponse) {
        view?.passwordsSuccess()
        view?.setUserDefaultInfo(user)
    }

    override fun onSuggestResultSuccess(data: List<SuggestResponse>) {
        view?.setSuggests(data)
    }

    override fun onResultFail(strError: String) {
        view?.toggleContentPB(false)
        if (strError == "IncorrectOldPass") view?.errorPasswordOld("Неверный пароль")
        else if (strError == "IncorrectPass") view?.errorPasswordNew("Неверный формат")
        else if (strError == "regexFirstName") view?.errorFirstName("Недопустимые символы")
        else if (strError == "regexLastName") view?.errorLastName("Недопустимые символы")
        else view?.showMessage(strError)
    }

    override fun userInfoSended() {
        view?.userInfoSended()
        view?.passwordsSuccess()
    }

    fun getAddressList(context: Context?, query: String) {
        model?.getSuggests(Settings(context!!).getProperty("token")!!, query, this)
    }

    fun getUserInfo(context: Context?) {
        val token = Settings(context!!).getProperty("token")
        if (token != null)
            model?.getUserInfoData(token, this)
    }

    fun sendUserImage(filePath: String?, context: Context?) {
        val token = Settings(context!!).getProperty("token")
        if (token != null)
            model?.sendUserImage(token, filePath, this)
    }

    fun sendUserInfo(
        first_name: String,
        last_name: String,
        password_old: String?,
        password: String?,
        password_confirmation: String?,
        address: String?, context: Context?,
        dialogDismiss: Boolean
    ) {
        if (first_name == "" || last_name == "" || ((password == "" || password_confirmation == "" || password_old == "") && !dialogDismiss)) {
            view?.hidePasswordError()
            view?.hideOtherError()
            if (first_name == "")
                view?.userNameClear("Пустое поле")
            if (last_name == "")
                view?.userLastNameClear("Пустое поле")
            if (password == "" && !dialogDismiss)
                view?.errorPasswordNew("Пустое поле")
            if (password_confirmation == "" && !dialogDismiss)
                view?.errorConfirmPass("Пустое поле")
            if (password_old == "" && !dialogDismiss)
                view?.errorPasswordOld("Пустое поле")
        } else if (password_old != null && password != null && password_confirmation != null &&(password_old.length !in 6..256 || password.length !in 6..256 || password_confirmation.length !in 6..256) && !dialogDismiss) {
            view?.hidePasswordError()
            view?.hideOtherError()
            if (password_old.length < 6)
                view?.errorPasswordOld(context!!.getString(R.string.min_6_symbols))
            if (password_old.length > 255)
                view?.errorPasswordOld(context!!.getString(R.string.min_255_symbols))
            if (password.length < 6 || password_confirmation.length < 6) {
                if (password.length < 6)
                    view?.errorPasswordNew(context!!.getString(R.string.min_6_symbols))
                if (password_confirmation.length < 6)
                    view?.errorConfirmPass(context!!.getString(R.string.min_6_symbols))
            }
            if ((password.length > 255 || password_confirmation.length > 255) && !dialogDismiss) {
                if (password.length > 255)
                    view?.errorPasswordNew(context!!.getString(R.string.min_255_symbols))
                if (password_confirmation.length > 255)
                    view?.errorConfirmPass(context!!.getString(R.string.min_255_symbols))
            }
        } else if ((password != password_confirmation) && !dialogDismiss) {
            view?.hidePasswordError()
            view?.hideOtherError()
            view?.errorPasswordNew(context!!.getString(R.string.passwords_not_similar))
        } else {
            view?.hidePasswordError()
            view?.hideOtherError()
            view?.toggleContentPB(true)
            val token = Settings(context!!).getProperty("token")
            if (token != null)
                model?.sendUserInfoData(
                    token,
                    first_name,
                    last_name,
                    password_old,
                    password,
                    password_confirmation,
                    if (address == "") null
                    else address,
                    this
                )
        }
    }

    fun attachView(view: IUserProfileView?) {
        this.view = view
    }

    fun dettachView() {
        view = null
    }
}