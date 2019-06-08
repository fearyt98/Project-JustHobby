package com.aurimteam.justhobby.start.registry.start

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.LoginResponse
import com.aurimteam.justhobby.response_body.RegistryBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistryStartModel : IRegistryStartModel {
    interface OnFinishedListener {
        fun onResultSuccess(token: String, userId: Long)
        fun onResultFail(error: String)
    }

    override fun sendUserInfoData(
        first_name: String,
        last_name: String,
        email: String,
        password: String,
        password_confirmation: String,
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .registry(RegistryBody(first_name, last_name, email, password, password_confirmation))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccess(responseBody.token, responseBody.user_id)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message").toString())
                    }
                }
            })
    }
}