package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularPlaces

import com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces.IPopularPlacesModel
import com.aurimteam.justhobby.Response.PopularPlacesResponse

class PopularPlacesModel : IPopularPlacesModel {
    interface OnFinishedListener {
        fun onResultSuccess(places: List<PopularPlacesResponse>)  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun getPopularPlacesData(onFinishedListener: OnFinishedListener) {
        val places: List<PopularPlacesResponse> = listOf()
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
        onFinishedListener.onResultSuccess(places)
    }
}