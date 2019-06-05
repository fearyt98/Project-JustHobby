package com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses

import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.IPopularCoursesModel
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.IdentifierResponse
import java.sql.Timestamp

class PopularCoursesModel : IPopularCoursesModel {
    interface OnFinishedListener {
        fun onResultSuccess(courses: List<CourseResponse>)  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun getPopularCoursesData(onFinishedListener: OnFinishedListener) {
        val courses: List<CourseResponse> = listOf(
            CourseResponse(
                "sport",
                0,
                "Восточный брейк",
                "Drive - школа зажигательных танцев",
                "пр. Ленина 286, д. 12",
                IdentifierResponse("sport", 100),
                IdentifierResponse("Жиг", 101),
                Timestamp(100),
                Timestamp(200)
            ),
            CourseResponse(
                "dance",
                0,
                "Восточный базар",
                "Drive - школа зажигательных танцев",
                "пр. Ленина 26, д. 13",
                IdentifierResponse("sport", 100),
                IdentifierResponse("Жиг", 101),
                Timestamp(100),
                Timestamp(200)
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
        onFinishedListener.onResultSuccess(courses)
    }
}