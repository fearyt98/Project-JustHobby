package com.aurimteam.justhobby.start.registry.start

import android.content.Context
import com.aurimteam.justhobby.Settings

class RegistryStartPresenter(
    private var view: IRegistryStartView?,
    private val model: IRegistryStartModel?,
    private var context: Context?
) : RegistryStartModel.OnFinishedListener {

    override fun onResultSuccess(token: String, userId: Long) {
        if (token != "") {
            Settings(context!!).setProperty("token", token)
            Settings(context!!).setProperty("user_id", userId.toString())
            view?.userRegistrited()
        }
    }

    override fun onResultFail(error: String) {

    }

    fun sendUserInfo(
        first_name: String,
        last_name: String,
        email: String,
        password: String,
        password_confirmation: String
    ) {
        model?.sendUserInfoData(first_name, last_name, email, password, password_confirmation, this)
    }

    fun onDestroy() {
        context = null
        view = null
    }
}