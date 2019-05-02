package com.aurimteam.justhobby.Api

import com.aurimteam.justhobby.Body.AuthBody
import com.aurimteam.justhobby.Response.AuthResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @Headers("Accept: application/json")
    @POST("login")
    fun authorization(@Body authBody: AuthBody): Call<AuthResponse>
}