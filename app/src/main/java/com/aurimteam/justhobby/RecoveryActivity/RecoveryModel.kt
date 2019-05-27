package com.aurimteam.justhobby.RecoveryActivity

class RecoveryModel: IRecoveryModel {
    interface onFinishedListener{
        fun onResultSuccess()
        fun onResultFail()
    }
}