package com.aurimteam.justhobby.Main.Home

import com.aurimteam.justhobby.Response.TimelineResponses

class HomeTimeLineModel : IHomeModel {
    interface OnFinishedListener {
        fun onResultSuccess(eventsTimeLine: List<TimelineResponses>)  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun getEventsTimeLine(onFinishedListener: OnFinishedListener) {
        val events: List<TimelineResponses> = listOf(
            TimelineResponses(
                0,
                "01:20",
                "Брейк-данс (Начинающие)",
                "Александра Максимова",
                "Drive - школа танцев",
                " (ул. Вершинина, 25)",
                "sport"
            ),
            TimelineResponses(
                1,
                "20:30",
                "Стрип-пластика",
                "Евгения Иванова",
                "Стип - школа эротических танцев",
                " (ул. Лыткина, 18)",
                "dance"
            ),
            TimelineResponses(
                0,
                "01:20",
                "Брейк-данс (Начинающие)",
                "Александра Максимова",
                "Drive - школа танцев",
                " (ул. Вершинина, 25)",
                "languages"
            ),
            TimelineResponses(
                1,
                "20:30",
                "Стрип-пластика",
                "Евгения Иванова",
                "Стип - школа эротических танцев",
                " (ул. Лыткина, 18)",
                "languages"
            ),
            TimelineResponses(
                0,
                "01:20",
                "Брейк-данс (Начинающие)",
                "Александра Максимова",
                "Drive - школа танцев",
                " (ул. Вершинина, 25)",
                "dance"
            ),
            TimelineResponses(
                1,
                "20:30",
                "Стрип-пластика",
                "Евгения Иванова",
                "Стип - школа эротических танцев",
                " (ул. Лыткина, 18)",
                "dance"
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
        onFinishedListener.onResultSuccess(events)
    }
}