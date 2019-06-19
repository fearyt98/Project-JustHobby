package com.aurimteam.justhobby.user.main.home.home

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.EventResponse
import com.aurimteam.justhobby.response.TimelineNearDayResponse
import com.aurimteam.justhobby.response.TimelineResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeTimelineModel : IHomeModel {
    interface OnFinishedListener {
        fun onResultSuccessNearDayTimeline(nearDay: TimelineNearDayResponse)
        fun onResultSuccessTimeline(eventsTimeline: List<EventResponse>)
        fun onResultFail(strError: String?)
    }

    override fun getNearDayTimeline(isNext: Boolean, token: String, onFinishedListener: OnFinishedListener) {
            App.retrofit
                .create(Api::class.java)
                .getNearDayTimeline(token, if (isNext) true else null)
                .enqueue(object : Callback<TimelineNearDayResponse> {
                    override fun onFailure(call: Call<TimelineNearDayResponse>, t: Throwable) {
                        onFinishedListener.onResultFail(t.message)
                    }

                    override fun onResponse(
                        call: Call<TimelineNearDayResponse>,
                        response: Response<TimelineNearDayResponse>
                    ) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onFinishedListener.onResultSuccessNearDayTimeline(responseBody)
                        } else {
                            val jsonObj = JSONObject(response.errorBody()?.string())
                            onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                        }
                    }
                })

    }

    override fun getEventsTimeline(token: String, date: String, onFinishedListener: OnFinishedListener) {
            App.retrofit
                .create(Api::class.java)
                .getTimeline(token, date)
                .enqueue(object : Callback<TimelineResponse> {
                    override fun onFailure(call: Call<TimelineResponse>, t: Throwable) {
                        onFinishedListener.onResultFail(t.message)
                    }

                    override fun onResponse(call: Call<TimelineResponse>, response: Response<TimelineResponse>) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onFinishedListener.onResultSuccessTimeline(responseBody.data)
                        } else {
                            val jsonObj = JSONObject(response.errorBody()?.string())
                            onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                        }
                    }
                })

    }
}