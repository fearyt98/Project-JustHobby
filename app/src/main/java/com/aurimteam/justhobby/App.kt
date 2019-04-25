package com.aurimteam.justhobby

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application(){
    companion object{
        private val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://justhobby.herokuapp.com/api/") //api-адрес к серверу
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}