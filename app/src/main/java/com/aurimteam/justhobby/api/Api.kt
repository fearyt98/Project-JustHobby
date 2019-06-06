package com.aurimteam.justhobby.api

import com.aurimteam.justhobby.response.*
import com.aurimteam.justhobby.response_body.LoginBody
import com.aurimteam.justhobby.response_body.LogoutBody
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

    @Headers("Accept: application/json")
    @GET("user/timeline/near_day")
    fun getNearDayTimeline(
        @Query("token") token: String
    ): Call<TimelineNearDayResponse>

    @Headers("Accept: application/json")
    @GET("user/timeline")
    fun getTimeline(
        @Query("token") token: String,
        @Query("date") date: String
    ): Call<TimelineResponse>

    @Headers("Accept: application/json")
    @GET("user")
    fun getUser(
        @Query("token") token: String
    ): Call<UserResponse>

    @Headers("Accept: application/json")
    @GET("user/groups?included[groups][]=course&include[course][]=company")
    fun getUserGroups(
        @Query("token") token: String
    ): Call<GroupsResponse>
}