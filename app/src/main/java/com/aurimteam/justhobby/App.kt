package com.aurimteam.justhobby

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    companion object {
        private val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://justhobby.herokuapp.com/api/") //api-адрес к серверу
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val IMAGE_PICK_CODE = 10
        val PERMISSION_STORAGE_CODE = 11
        val CHANNEL_ID = "0"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationsChannel()
    }

    private fun createNotificationsChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID, "NotifyService", NotificationManager.IMPORTANCE_DEFAULT
            )
            val notifyManager: NotificationManager = getSystemService(NotificationManager::class.java)
            notifyManager.createNotificationChannel(notificationChannel)
        }
    }

}