package com.aurimteam.justhobby.start.recovery

class RecoveryPresenter(private var view: IRecoveryView?, private val model: IRecoveryModel) :
    RecoveryModel.OnFinishedListener {

    override fun onResultSuccess() {

    }

    override fun onResultFail(strError: String?) {

    }

    fun onDestroy(){
        view = null
    }
}