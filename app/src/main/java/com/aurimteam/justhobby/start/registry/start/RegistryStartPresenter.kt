package com.aurimteam.justhobby.start.registry.start

import android.content.Context
import android.net.Uri
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Settings

class RegistryStartPresenter(
    private var view: IRegistryStartView?,
    private val model: IRegistryStartModel?,
    private var context: Context?
) : RegistryStartModel.OnFinishedListener {
    private var countQueryClose = 0

    override fun onResultSuccess() {
        countQueryClose--
        if(countQueryClose <= 0) {
            view?.hideErrors()
            Settings(context!!).setPropertyBoolean("is_full_reg", true)
            view?.userRegistered()
            view?.togglePB(false)
        }
    }

    override fun onResultFail(error: String?) {
        view?.hideErrors()
        view?.togglePB(false)
        when (error) {
            "regexFirstName" -> view?.errorFirstName("Недопустимые символы")
            "regexLastName" -> view?.errorLastName("Недопустимые символы")
            else -> view?.showMessage(error)
        }
    }

    fun sendUserImage(filePath: Uri) {
        val token = Settings(context!!).getProperty("token")
        if (token != null) {
            view?.togglePB(true)
            countQueryClose++
            model?.sendUserImage(token, filePath, context!!, this)
        }
    }

    fun sendUserInfo(first_name: String, last_name: String) {
        if (first_name == "" || last_name == "") {
            view?.hideErrors()
            if (first_name == "")
                view?.clearFirstName(context!!.getString(R.string.empty_field))
            if (last_name == "")
                view?.clearLastName(context!!.getString(R.string.empty_field))
        } else if ((first_name.length !in 1..256) || last_name.length !in 1..256) {
            view?.hideErrors()
            if (first_name.isEmpty())
                view?.changeLengthFirstName(context!!.getString(R.string.empty_field))
            if (first_name.length > 255)
                view?.changeLengthFirstName(context!!.getString(R.string.min_255_symbols))
            if (last_name.isEmpty())
                view?.changeLengthLastName(context!!.getString(R.string.empty_field))
            if (last_name.length > 255)
                view?.changeLengthLastName(context!!.getString(R.string.min_255_symbols))
        } else {
            val token = Settings(context!!).getProperty("token")
            if (token != null) {
                view?.togglePB(true)
                countQueryClose++
                model?.sendUserInfoData(token, first_name, last_name, this)
            }
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