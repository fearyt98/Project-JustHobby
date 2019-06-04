package com.aurimteam.justhobby.user.main.settings

import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.response.LogoutResponse
import com.aurimteam.justhobby.response_body.LogoutBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsModel : ISettingsModel {
    interface OnFinishedListener {
        fun onResultSuccess()
        fun onResultFail(strError: String)
    }

    override fun logoutUser(token: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .logout(LogoutBody(token))
            .enqueue(object : Callback<LogoutResponse> {
                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                    val logoutResponse = response.body()
                    if (logoutResponse != null) {
                        onFinishedListener.onResultSuccess()
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error").getString("message").toString())
                    }
                }
            })
    }
}