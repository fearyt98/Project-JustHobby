package com.aurimteam.justhobby.user.main.home.home

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.EventResponse
import com.aurimteam.justhobby.response.TimelineResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeTimeLineModel : IHomeModel {
    interface OnFinishedListener {
        fun onResultSuccess(eventsTimeline: List<EventResponse>)
        fun onResultFail(strError: String?)
    }

    override fun getEventsTimeLine(Token: String, date: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getTimeline(Token, date)
            .enqueue(object : Callback<TimelineResponse> {
                override fun onFailure(call: Call<TimelineResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<TimelineResponse>, response: Response<TimelineResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccess(responseBody.data)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }
}