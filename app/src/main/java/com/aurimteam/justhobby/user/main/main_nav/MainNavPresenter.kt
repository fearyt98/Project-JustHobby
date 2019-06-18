package com.aurimteam.justhobby.user.main.main_nav

import android.content.Context
import com.aurimteam.justhobby.Settings

class MainNavPresenter(private var view: IMainNavView?, private val model: IMainNavModel?, private var context: Context?) :
    MainNavModel.OnFinishedListener {

    override fun onResultFail(strError: String?) {
        //view?.showMessage(strError)
        Settings(context!!).removeProperty("token")
        Settings(context!!).removeProperty("user_id")
        view?.openAuth()
    }

    fun checkToken() {
        val token = Settings(context!!).getProperty("token")
        if (token != null)
            model?.getUser(token, this)
        else
            view?.openAuth()
    }

    fun isSetViewContext(): Boolean {
        return view == null || context != null
    }

    fun attachViewContext(view: IMainNavView?, context: Context) {
        this.context = context
        this.view = view
    }

    fun onDestroy() {
        context = null
        view = null
    }
}