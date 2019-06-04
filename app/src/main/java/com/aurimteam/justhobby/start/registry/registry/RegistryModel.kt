package com.aurimteam.justhobby.start.registry.registry

class RegistryModel: IRegistryModel {
    interface OnFinishedListener{
        fun onResultSuccess()
        fun onResultFail()
    }

    override fun sendUserInfoData(onFinishedListener: OnFinishedListener) {

    }
}