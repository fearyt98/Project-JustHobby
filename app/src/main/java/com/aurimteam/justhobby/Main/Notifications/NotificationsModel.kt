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
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                1,
                "май",
                1557117152,
                false
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                2,
                "май",
                1557117152,
                false
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                3,
                "май",
                1557117152,
                false
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                4,
                "май",
                1557117152,
                false
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                5,
                "май",
                1557117152,
                false
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                6,
                "май",
                1557117152,
                false
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                7,
                "май",
                1557117152,
                false
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                8,
                "май",
                1557117152,
                true
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                9,
                "май",
                1557117152,
                true
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                10,
                "май",
                1557117152,
                false
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                11,
                "май",
                1557117152,
                false
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                12,
                "май",
                1557117152,
                false
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                13,
                "май",
                1557117152,
                true
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                14,
                "май",
                1557117152,
                true
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                15,
                "май",
                1557117152,
                true
            ),
            NotificationResponse(
                "Восточные танцы",
                "Текст уведомления. Он будет здесь. Вот так. А ты что хотел? :)",
                16,
                "май",
                1557117152,
                true
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