package com.aurimteam.justhobby.start.registry.start

import android.content.Context
import android.net.Uri

interface IRegistryStartModel {
    fun sendUserInfoData(
        token: String,
        first_name: String, last_name: String, onFinishedListener: RegistryStartModel.OnFinishedListener
    )

    fun sendUserImage(
        token: String,
        filePath: Uri,
        context: Context,
        onFinishedListener: RegistryStartModel.OnFinishedListener
    )
}