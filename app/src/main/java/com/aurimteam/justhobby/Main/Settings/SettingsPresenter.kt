package com.aurimteam.justhobby.Main.Settings

import android.content.Context
import com.aurimteam.justhobby.Response.LoginResponse
import com.aurimteam.justhobby.Settings

class SettingsPresenter(
    private var view: ISettingsView?,
    private val model: ISettingsModel?,
    private var context: Context?
) : SettingsModel.OnFinishedListener {

    override fun onResultSuccess() {
        Settings(context!!).removeProperty("token")
        Settings(context!!).removeProperty("user_id")
        view!!.openAuth()
    }

    override fun onResultFail(strError: String) {
        if(strError == "Wrong token") {
            onResultSuccess()
        } else {
            view!!.showMessage(strError)
        }
    }

    fun logoutUser() {
        if (Settings(context!!).getProperty("token") != null)
            model?.logoutUser(Settings(context!!).getProperty("token")!!, this)
    }

    fun onDestroy() {
        view = null
    }
}