package com.aurimteam.justhobby.user.main.settings.settings

import android.content.Context
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

    override fun onResultFail(strError: String?) {
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

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachViewContext(view: ISettingsView?, context: Context) {
        this.context = context
        this.view = view
    }

    fun onDestroy() {
        context = null
        view = null
    }
}