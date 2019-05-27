package com.aurimteam.justhobby.RecoveryActivity

class RecoveryPresenter(private var view: IRecoveryView?, private val model: IRecoveryModel) :
    RecoveryModel.onFinishedListener {

    override fun onResultSuccess() {

    }

    override fun onResultFail() {

    }

    fun onDestroy(){
        view = null
    }
}