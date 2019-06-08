package com.aurimteam.justhobby.start.registry.registry

interface IRegistryModel {
    fun sendUserInfoData(
        email: String,
        password: String,
        confirmPassword: String,
        onFinishedListener: RegistryModel.OnFinishedListener
    )
}