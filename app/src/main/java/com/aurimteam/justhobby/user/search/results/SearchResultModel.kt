package com.aurimteam.justhobby.user.search.results

import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.IdentifierResponse
import java.sql.Timestamp

class SearchResultModel : ISearchResultModel {
    interface OnFinishedListener {
        fun onResultSuccess(foundedCourses: List<CourseResponse>)
        fun onResultSuccess(number: Int)
        fun onResultFail()
    }

    override fun getSearchResultsData(onFinishedListener: OnFinishedListener) {
        val foundedCourses: List<CourseResponse> = listOf(/*
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
            ),
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
            )
        */)
        onFinishedListener.onResultSuccess(foundedCourses)
    }
}