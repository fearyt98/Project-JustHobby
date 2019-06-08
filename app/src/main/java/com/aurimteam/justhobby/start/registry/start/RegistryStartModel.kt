package com.aurimteam.justhobby.start.registry.start

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.TokenResponse
import com.aurimteam.justhobby.response.UserResponse
import com.aurimteam.justhobby.response_body.RegistryBody
import com.aurimteam.justhobby.response_body.UpdateUserBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistryStartModel : IRegistryStartModel {
    interface OnFinishedListener {
        fun onResultSuccess()
        fun onResultFail(error: String)
    }

    override fun sendUserInfoData(
        token: String,
        first_name: String,
        last_name: String,
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .updateUser(UpdateUserBody(token, first_name, last_name))
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccess()
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message").toString())
                    }
                }
            })
    }
}