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
        view?.back()
    }

    override fun onResultFail(strError: String?) {
        view?.togglePB(false)
        view?.showMessage(strError)
    }

    override fun wrongEmail(strError: String) {
        view?.togglePB(false)
        if (strError == "The given data was invalid")
            view?.emailError(context!!.getString(R.string.wrong_data))
        else view?.showMessage(strError)
    }

    fun sendRecoveryEmail(email: String) {
        if (email == "") {
            view?.hideError()
            view?.clearEmailError(context!!.getString(R.string.empty_field))
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

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachViewContext(view: IRecoveryView?, context: Context) {
        this.context = context
        this.view = view
    }

    fun onDestroy() {
        context = null
        view = null
    }
}