package com.aurimteam.justhobby.start.auth

import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.response_body.LoginBody
import com.aurimteam.justhobby.response.LoginResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthModel : IAuthModel {

    interface OnFinishedListener {
        fun onResultSuccess(token: LoginResponse)
        fun onResultFail(strError: String)
    }

    override fun loginUser(login: String, password: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .login(LoginBody(login, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        onFinishedListener.onResultSuccess(loginResponse)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error").getString("message").toString())
                    }
                }
            })
    }
}