package com.aurimteam.justhobby.start.recovery

import android.content.Context

class RecoveryPresenter(
    private var view: IRecoveryView?,
    private val model: IRecoveryModel?,
    private var context: Context?
) : RecoveryModel.OnFinishedListener {

    override fun onResultSuccess() {
        view?.togglePB(false)
        view?.backToMainActivity()
    }

    override fun onResultFail(strError: String) {

    }

    fun sendRecoveryEmail(email: String) {
        view?.togglePB(true)
        model?.sendRecoveryEmail(email, this)
    }

    fun onDestroy() {
        context = null
        view = null
    }
}