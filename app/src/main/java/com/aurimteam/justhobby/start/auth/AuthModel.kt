package com.aurimteam.justhobby.start.auth

import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.response_body.LoginBody
import com.aurimteam.justhobby.response.TokenResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthModel : IAuthModel {

    interface OnFinishedListener {
        fun onResultSuccess(token: TokenResponse)
        fun onResultFail(strError: String)
    }

    override fun loginUser(login: String, password: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .login(LoginBody(login, password))
            .enqueue(object : Callback<TokenResponse> {
                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccess(responseBody)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message").toString())
                    }
                }
            })
    }
}