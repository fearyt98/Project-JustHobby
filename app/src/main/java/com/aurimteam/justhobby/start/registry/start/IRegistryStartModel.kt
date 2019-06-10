package com.aurimteam.justhobby.start.registry.start

interface IRegistryStartModel {
    fun sendUserInfoData(
        token: String,
        first_name: String, last_name: String, onFinishedListener: RegistryStartModel.OnFinishedListener
    )

    fun sendUserImage(
        token: String,
        filePath: String?,
        onFinishedListener: RegistryStartModel.OnFinishedListener
    )
}