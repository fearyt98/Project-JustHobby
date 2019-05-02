package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularCourses

import com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces.IPopularCoursesModel
import com.aurimteam.justhobby.Response.CourseResponse

class PopularCoursesModel : IPopularCoursesModel {
    interface OnFinishedListener {
        fun onResultSuccess(places: List<CourseResponse>)  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun getPopularCoursesData(onFinishedListener: OnFinishedListener) {
        val places: List<CourseResponse> = listOf()
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