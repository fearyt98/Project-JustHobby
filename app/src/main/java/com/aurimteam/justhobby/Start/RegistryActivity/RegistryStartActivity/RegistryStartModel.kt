package com.aurimteam.justhobby.Start.RegistryActivity.RegistryStartActivity

class RegistryStartModel : IRegistryStartModel {
    interface onFinishedListener {
        fun onResultSuccess()
        fun onResultFail()
    }

    override fun sendUserInfoData(onFinishedListener: onFinishedListener) {

        onFinishedListener.onResultSuccess()
    }
}