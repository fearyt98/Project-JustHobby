package com.aurimteam.justhobby.start.registry.registry

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.TokenResponse
import com.aurimteam.justhobby.response_body.RegistryBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistryModel : IRegistryModel {
    interface OnFinishedListener {
        fun onResultSuccess(data: TokenResponse)
        fun onResultFail(error: String)
    }

    override fun sendUserInfoData(
        email: String,
        password: String,
        confirmPassword: String,
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .registry(RegistryBody(email, password, confirmPassword))
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
                        if (jsonObj.getJSONObject("error")?.has("description") != null) {
                            val description = jsonObj.getJSONObject("error")?.getJSONObject("description")!!
                            if (description.has("email")) {
                                if (description.getJSONObject("email").has("Unique"))
                                    onFinishedListener.onResultFail("Unique")
                                if (description.getJSONObject("email").has("Email"))
                                    onFinishedListener.onResultFail("IncorrectEmail")
                            }
                            if (description.has("password")) {
                                if (description.getJSONObject("password").has("Regex"))
                                    onFinishedListener.onResultFail("IncorrectPass")
                            }
                        }
                    }
                }
            })

    }
}