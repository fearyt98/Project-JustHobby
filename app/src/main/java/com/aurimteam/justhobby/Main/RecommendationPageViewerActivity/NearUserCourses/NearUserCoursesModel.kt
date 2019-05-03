package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.NearUserCourses

import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.FragmentsInterfaces.INearCoursesModel
import com.aurimteam.justhobby.Response.CourseResponse
import com.aurimteam.justhobby.Response.IdentifierResponse
import java.sql.Timestamp

class NearUserCoursesModel : INearCoursesModel {
    interface OnFinishedListener {
        fun onResultSuccess(courses: List<CourseResponse>)  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun getNearCoursesData(onFinishedListener: OnFinishedListener) {
        val courses: List<CourseResponse> = listOf(
            CourseResponse(
                "sport",
                0,
                "Восточный дракон",
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
                "Восточный Шаолинь",
                "Drive - школа зажигательных танцев",
                "пр. Ленина 26, д. 13",
                IdentifierResponse("sport", 100),
                IdentifierResponse("Жиг", 101),
                Timestamp(100),
                Timestamp(200)
            )
        )
        onFinishedListener.onResultSuccess(courses)
    }
}