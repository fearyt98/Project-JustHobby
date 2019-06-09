package com.aurimteam.justhobby.start.recovery

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.StatusResponse
import com.aurimteam.justhobby.response_body.RecoveryBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecoveryModel: IRecoveryModel {
    interface OnFinishedListener{
        fun onResultSuccess()
        fun onResultFail(strError: String)
        fun wrongEmail(strError: String)
    }

    override fun sendRecoveryEmail(email: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .recallEmail(RecoveryBody(email))
            .enqueue(object : Callback<StatusResponse> {
                override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccess()
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.wrongEmail(jsonObj.getJSONObject("error")?.getString("message").toString())
                    }
                }
            })
    }
}