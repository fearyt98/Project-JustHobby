package com.aurimteam.justhobby.AuthActivity

import com.aurimteam.justhobby.Api.Api
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.Response.AuthBody
import com.aurimteam.justhobby.Response.AuthResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthModel : IAuthModel {

    interface OnFinishedListener {
        fun onResultSuccess(token: AuthResponse)  //arrUpdates: List<DataItem>
        fun onResultFail(strError: String) //strError: String
    }

    override fun checkUserData(loginMain: String, password: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .authorization(AuthBody(loginMain, password))
            .enqueue(object : Callback<AuthResponse> {
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    val tokenResponse = response.body()
                    if (tokenResponse != null) {
                        onFinishedListener.onResultSuccess(tokenResponse)

                    } else {
                        val jsonObj: JSONObject = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getString("message").toString())
                    }
                }
            })
    }
}