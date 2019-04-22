package com.aurimteam.justhobby.Main.HomeActivity

import com.aurimteam.justhobby.Response.TimeLineEventResponse

class HomeModel : IHomeModel {
    interface OnFinishedListener {
        fun onResultSuccess(eventsTimeLine: List<TimeLineEventResponse>)  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun getEventsTimeLine(onFinishedListener: OnFinishedListener) {
        val events: List<TimeLineEventResponse> = listOf(
            TimeLineEventResponse(
                0,
                "01:20",
                "Брейк-данс (Начинающие)",
                "Александра Максимова",
                "Drive - школа танцев",
                " (ул. Вершинина, 25)"
            ),
            TimeLineEventResponse(
                1,
                "20:30",
                "Стрип-пластика",
                "Евгения Иванова",
                "Стип - школа эротических танцев",
                " (ул. Лыткина, 18)"
            ),
            TimeLineEventResponse(
                0,
                "01:20",
                "Брейк-данс (Начинающие)",
                "Александра Максимова",
                "Drive - школа танцев",
                " (ул. Вершинина, 25)"
            ),
            TimeLineEventResponse(
                1,
                "20:30",
                "Стрип-пластика",
                "Евгения Иванова",
                "Стип - школа эротических танцев",
                " (ул. Лыткина, 18)"
            ),
            TimeLineEventResponse(
                0,
                "01:20",
                "Брейк-данс (Начинающие)",
                "Александра Максимова",
                "Drive - школа танцев",
                " (ул. Вершинина, 25)"
            ),
            TimeLineEventResponse(
                1,
                "20:30",
                "Стрип-пластика",
                "Евгения Иванова",
                "Стип - школа эротических танцев",
                " (ул. Лыткина, 18)"
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