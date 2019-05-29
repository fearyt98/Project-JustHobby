package com.aurimteam.justhobby.Api

import com.aurimteam.justhobby.Response.CategoriesResponse
import com.aurimteam.justhobby.ResponseBody.LoginBody
import com.aurimteam.justhobby.Response.LoginResponse
import com.aurimteam.justhobby.Response.LogoutResponse
import com.aurimteam.justhobby.ResponseBody.LogoutBody
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @Headers("Accept: application/json")
    @POST("login")
    fun login(@Body loginBody: LoginBody): Call<LoginResponse>

    @Headers("Accept: application/json")
    @HTTP(method = "DELETE", path = "logout", hasBody = true)
    fun logout(@Body logoutBody: LogoutBody): Call<LogoutResponse>

    @Headers("Accept: application/json")
    @GET("categories")
    fun getCategories(
        @Query("token") token: String,
        @Query("page[size]") page_size: Int,
        @Query("page[number]") page_number: Int
    ): Call<CategoriesResponse>
}