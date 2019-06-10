package com.aurimteam.justhobby.user.main.settings.user_profile

import android.content.Context
import com.aurimteam.justhobby.Settings

class UserProfilePresenter(private var view: IUserProfileView?, private val model: IUserProfileModel?) :
    UserProfileModel.OnFinishedListener {

    override fun onResultSuccess(email: String, name: String, lastName: String, address: String?) {
        view?.setUserDefaultInfo(email, name, lastName, address)
    }

    override fun onResultFail(strError: String) {
        view?.toggleContentPB(false)
        view?.showMessage(strError)
    }

    override fun userInfoSended() {
        view?.toggleContentPB(false)
    }

    fun getUserInfo(context: Context?) {
        val token = Settings(context!!).getProperty("token")
        if (token != null)
            model?.getUserInfoData(token, this)
    }
    fun sendUserImage(filePath: String?, context: Context?) {
        val token = Settings(context!!).getProperty("token")
        model?.sendUserImage(token!!, filePath, this)
    }

    fun sendUserInfo(
        first_name: String,
        last_name: String,
        password_old: String,
        password: String,
        password_confirmation: String,
        address: String, context: Context?
    ) {
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
                address,
                this
            )
    }

    fun attachView(view: IUserProfileView?) {
        this.view = view
    }

    fun dettachView() {
        view = null
    }
}