package com.aurimteam.justhobby.CompanyInfo.CompanyCoursesInfoActivity

import com.aurimteam.justhobby.CompanyInfo.CompanyInfoMainActivity.CompanyInfoModel
import com.aurimteam.justhobby.Response.CourseResponse
import com.aurimteam.justhobby.Response.IdentifierResponse
import java.sql.Timestamp

class CompanyCoursesModel : ICompanyCoursesModel{
    interface onFinishedListener{
        fun onResultSuccess(courses: List<CourseResponse>)
        fun onResultFail()
    }
    override fun getCompanyCoursesData(onFinishedListener: onFinishedListener) {
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
            )
        )
        onFinishedListener.onResultSuccess(courses)
    }
}