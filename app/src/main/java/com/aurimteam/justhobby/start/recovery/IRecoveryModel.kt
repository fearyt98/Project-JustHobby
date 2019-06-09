package com.aurimteam.justhobby.start.recovery

interface IRecoveryModel {
    fun sendRecoveryEmail(email: String, onFinishedListener: RecoveryModel.OnFinishedListener)
}