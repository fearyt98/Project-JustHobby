package com.aurimteam.justhobby.user.main.notifications

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.NotificationsResponse
import com.aurimteam.justhobby.response.StatusResponse
import com.aurimteam.justhobby.response_body.TokenBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsModel : INotificationsModel {

    interface OnFinishedListener {
        fun onResultSuccessNew(notifications: NotificationsResponse)
        fun onResultSuccessOld(notifications: NotificationsResponse)
        fun onResultFail(strError: String?)
        fun onResultSuccessDelete()
    }

    override fun getNotificationsData(token: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getUserNotify(token, null, true)
            .enqueue(object : Callback<NotificationsResponse> {
                override fun onFailure(call: Call<NotificationsResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(
                    call: Call<NotificationsResponse>,
                    response: Response<NotificationsResponse>
                ) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccessNew(responseBody)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
        App.retrofit
            .create(Api::class.java)
            .getUserNotify(token, true, true)
            .enqueue(object : Callback<NotificationsResponse> {
                override fun onFailure(call: Call<NotificationsResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(
                    call: Call<NotificationsResponse>,
                    response: Response<NotificationsResponse>
                ) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccessOld(responseBody)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }

    override fun deleteAllNotify(token: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .deleteAllNotify(TokenBody(token))
            .enqueue(object : Callback<StatusResponse> {
                override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(
                    call: Call<StatusResponse>,
                    response: Response<StatusResponse>
                ) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccessDelete()
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }
}