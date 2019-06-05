package com.aurimteam.justhobby.start.registry.start

class RegistryStartModel : IRegistryStartModel {
    interface OnFinishedListener {
        fun onResultSuccess()
        fun onResultFail()
    }

    override fun sendUserInfoData(onFinishedListener: OnFinishedListener) {

        onFinishedListener.onResultSuccess()
    }
}