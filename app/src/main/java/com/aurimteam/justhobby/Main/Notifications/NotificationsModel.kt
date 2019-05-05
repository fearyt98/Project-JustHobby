package com.aurimteam.justhobby.Main.Notifications

import com.aurimteam.justhobby.Response.NotificationResponse
import java.sql.Timestamp

class NotificationsModel : INotificationsModel {
    interface OnFinishedListener {
        fun onResultSuccess(notifications: List<NotificationResponse>)  //arrUpdates: List<DataItem>eventsTimeLine: List<TimelineResponses>
        fun onResultFail() //strError: String
    }

    override fun getNotificationsData(onFinishedListener: OnFinishedListener) {
        val notifications: List<NotificationResponse> = listOf(
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                1,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                2,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                3,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                4,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                5,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                6,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                7,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                8,
                "jan",
                Timestamp(100),
                true
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                9,
                "jan",
                Timestamp(100),
                true
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                10,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                11,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                12,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                13,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                14,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                15,
                "jan",
                Timestamp(100),
                false
            ),
            NotificationResponse(
                "privat",
                "gfhfghfgfghfg",
                16,
                "jan",
                Timestamp(100),
                false
            )
        )
        /*App.retrofit
            .create(Api::class.java)
            .getAllEvents()
            .enqueue(object : Callback<List<EventResponse>> {
                override fun onFailure(call: Call<List<EventResponse>>, t: Throwable) {
                    Log.e("asd", t.toString())
                }
                override fun onResponse(call: Call<List<EventResponse>>, response: Response<List<EventResponse>>) {
                    val events = response.body()

                    if (events != null) {
                        view?.showEvents(events)
                    }
                }
            })*/
        onFinishedListener.onResultSuccess(notifications)
    }
}