package com.aurimteam.justhobby.Start.RegistryActivity

class RegistryModel: IRegistryModel {
    interface onFinishedListener{
        fun onResultSuccess()
        fun onResultFail()
    }

    override fun sendUserInfoData(onFinishedListener: onFinishedListener) {

    }
}