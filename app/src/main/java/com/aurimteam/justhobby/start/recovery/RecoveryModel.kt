package com.aurimteam.justhobby.start.recovery

class RecoveryModel: IRecoveryModel {
    interface OnFinishedListener{
        fun onResultSuccess()
        fun onResultFail(strError: String?)
    }
}