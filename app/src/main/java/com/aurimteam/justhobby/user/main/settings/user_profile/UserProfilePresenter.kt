package com.aurimteam.justhobby.user.main.settings.user_profile

import android.content.Context
import android.net.Uri
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
        when (strError) {
            "IncorrectOldPass" -> view?.errorPasswordOld("Неверный пароль")
            "IncorrectPass" -> view?.errorPasswordNew("Неверный формат")
            "regexFirstName" -> view?.errorFirstName("Недопустимые символы")
            "regexLastName" -> view?.errorLastName("Недопустимые символы")
            else -> view?.showMessage(strError)
        }
    }

    override fun userInfoSended() {
        view?.userInfoSended()
    }

    override fun userPasswordSended() {
        view?.passwordsSuccess()
    }

    fun getAddressList(context: Context?, query: String) {
        val token = Settings(context!!).getProperty("token")
        if (token != null)
            model?.getSuggests(token, query, this)
    }

    fun getUserInfo(context: Context?) {
        val token = Settings(context!!).getProperty("token")
        if (token != null)
            model?.getUserInfoData(token, this)
    }

    fun sendUserImage(filePath: Uri, context: Context?) {
        val token = Settings(context!!).getProperty("token")
        if (token != null)
            model?.sendUserImage(token, filePath, context, this)
    }

    fun sendUserInfo(
        first_name: String,
        last_name: String,
        address: String,
        context: Context?
    ) {
        if (first_name == "" || last_name == "") {
            view?.hideOtherError()
            if (first_name == "")
                view?.userNameClear(context!!.getString(R.string.empty_field))
            if (last_name == "")
                view?.userLastNameClear(context!!.getString(R.string.empty_field))
        } else {
            view?.hideOtherError()
            view?.toggleContentPB(true)
            val token = Settings(context!!).getProperty("token")
            if (token != null)
                model?.sendUserInfoData(
                    token,
                    first_name,
                    last_name,
                    if (address.trim() == "") "null"
                    else address,
                    this
                )
        }
    }

    fun sendUserPassword(
        password_old: String,
        password: String,
        password_confirmation: String,
        context: Context?
    ) {
        if (password == "" || password_confirmation == "" || password_old == "") {
            view?.hidePasswordError()
            if (password == "")
                view?.errorPasswordNew(context!!.getString(R.string.empty_field))
            if (password_confirmation == "")
                view?.errorConfirmPass(context!!.getString(R.string.empty_field))
            if (password_old == "")
                view?.errorPasswordOld(context!!.getString(R.string.empty_field))
        } else if (password_old.length !in 6..256 || password.length !in 6..256 || password_confirmation.length !in 6..256) {
            view?.hidePasswordError()
            if (password_old.length < 6)
                view?.errorPasswordOld(context!!.getString(R.string.min_6_symbols))
            if (password_old.length > 255)
                view?.errorPasswordOld(context!!.getString(R.string.min_255_symbols))

            if (password.length < 6)
                view?.errorPasswordNew(context!!.getString(R.string.min_6_symbols))
            if (password.length > 255)
                view?.errorPasswordNew(context!!.getString(R.string.min_255_symbols))

            if (password_confirmation.length < 6)
                view?.errorConfirmPass(context!!.getString(R.string.min_6_symbols))
            if (password_confirmation.length > 255)
                view?.errorConfirmPass(context!!.getString(R.string.min_255_symbols))
        } else if (password != password_confirmation) {
            view?.hidePasswordError()
            view?.errorPasswordNew(context!!.getString(R.string.passwords_not_similar))
        } else {
            view?.hidePasswordError()
            view?.toggleContentPB(true)
            val token = Settings(context!!).getProperty("token")
            if (token != null)
                model?.sendUserPassword(
                    token,
                    password_old,
                    password,
                    password_confirmation,
                    this
                )
        }
    }

    fun attachView(view: IUserProfileView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}