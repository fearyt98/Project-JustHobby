package com.aurimteam.justhobby.start.recovery

import android.content.Context
import com.aurimteam.justhobby.R

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
        view?.togglePB(false)
        view?.showServerMessage(strError)
    }

    override fun wrongEmail(strError: String) {
        view?.togglePB(false)
        if (strError == "The given data was invalid")
            view?.emailError(context!!.getString(R.string.wrong_data))
        else view?.showServerMessage(strError)
    }

    fun sendRecoveryEmail(email: String) {
        if (email == "") {
            view?.hideError()
            view?.clearEmailError(context!!.getString(R.string.emptyField))
        } else if (email.length !in 5..256) {
            view?.hideError()
            if (email.length < 5)
                view?.changeLengthEmail(context!!.getString(R.string.min_5_symbols))
            if (email.length > 255)
                view?.changeLengthEmail(context!!.getString(R.string.min_255_symbols))
        } else {
            view?.togglePB(true)
            model?.sendRecoveryEmail(email, this)
        }
    }

    fun onDestroy() {
        context = null
        view = null
    }
}