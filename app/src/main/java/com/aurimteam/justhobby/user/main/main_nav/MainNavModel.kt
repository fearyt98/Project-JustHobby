package com.aurimteam.justhobby.user.main.main_nav

import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.response.UserResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainNavModel : IMainNavModel {

    interface OnFinishedListener {
        fun onResultFail(strError: String)
    }

    override fun getUser(token: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getUser(token)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {

                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error").getString("message").toString())
                    }
                }
            })
    }
}