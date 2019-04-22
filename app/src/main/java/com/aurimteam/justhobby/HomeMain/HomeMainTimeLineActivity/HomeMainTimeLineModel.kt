package com.aurimteam.justhobby.HomeMain.HomeMainTimeLineActivity

import com.aurimteam.justhobby.Response.TimeLineEventResponse

class HomeMainTimeLineModel : IHomeMainModel {
    interface OnFinishedListener {
        fun onResultSuccess(eventsTimeLine: List<TimeLineEventResponse>)  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun getEventsTimeLine(onFinishedListener: OnFinishedListener) {
        val events: List<TimeLineEventResponse> = listOf(
            TimeLineEventResponse(0,"01:20","Брейк-данс","Александра","Drive - школа танцев"),
            TimeLineEventResponse(1,"20:30","Стрип-пластика","Евгения","Стип - школа эротических танцев")
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