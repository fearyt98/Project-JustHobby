package com.aurimteam.justhobby.start.registry.start

import android.content.Context
import com.aurimteam.justhobby.R
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
        view?.showMessage(error)
    }

    fun sendUserImage(filePath: String?) {
        val token = Settings(context!!).getProperty("token")
        model?.sendUserImage(token!!, filePath, this)
    }

    fun sendUserInfo(first_name: String, last_name: String) {
        if (first_name == "" || last_name == "") {
            view?.hideErrors()
            if (first_name == "")
                view?.clearFirstName(context!!.getString(R.string.emptyField))
            if (last_name == "")
                view?.clearLastName(context!!.getString(R.string.emptyField))
        } else if ((first_name.length !in 1..256) || last_name.length !in 1..256) {
            view?.hideErrors()
            if (first_name.isEmpty())
                view?.changeLengthFirstName(context!!.getString(R.string.emptyField))
            if (first_name.length > 255)
                view?.changeLengthFirstName(context!!.getString(R.string.min_255_symbols))
            if (last_name.isEmpty())
                view?.changeLengthLastName(context!!.getString(R.string.emptyField))
            if (last_name.length > 255)
                view?.changeLengthLastName(context!!.getString(R.string.min_255_symbols))
        } else {
            view?.togglePB(true)
            val token = Settings(context!!).getProperty("token")
            model?.sendUserInfoData(token!!, first_name, last_name, this)
        }
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachViewContext(view: IRegistryStartView?, context: Context) {
        this.context = context
        this.view = view
    }

    fun onDestroy() {
        context = null
        view = null
    }
}