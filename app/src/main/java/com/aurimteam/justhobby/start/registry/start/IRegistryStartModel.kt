package com.aurimteam.justhobby.start.registry.start

interface IRegistryStartModel {
    fun sendUserInfoData(
        first_name: String,
        last_name: String,
        email: String,
        password: String,
        password_confirmation: String,
        onFinishedListener: RegistryStartModel.OnFinishedListener
    )
}