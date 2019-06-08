package com.aurimteam.justhobby.start.registry.registry

import android.content.Context
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
    }

    fun sendUserInfo(email: String, password: String, confirmPassword: String) {
        view?.togglePB(true)
        model?.sendUserInfoData(email, password, confirmPassword, this)
    }

    fun onDestroy() {
        context = null
        view = null
    }
}