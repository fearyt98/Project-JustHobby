package com.aurimteam.justhobby.start.registry.start

import android.content.Context
import com.aurimteam.justhobby.Settings

class RegistryStartPresenter(
    private var view: IRegistryStartView?,
    private val model: IRegistryStartModel?,
    private var context: Context?
) : RegistryStartModel.OnFinishedListener {

    override fun onResultSuccess() {
        Settings(context!!).setPropertyBoolean("is_full_reg", true)
        view?.userRegistered()
    }

    override fun onResultFail(error: String) {
        view?.togglePB(false)
    }

    fun sendUserInfo(first_name: String, last_name: String) {
        view?.togglePB(true)
        val token = Settings(context!!).getProperty("token")
        model?.sendUserInfoData(token!!, first_name, last_name, this)
    }

    fun onDestroy() {
        context = null
        view = null
    }
}