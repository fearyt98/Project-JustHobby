package com.aurimteam.justhobby.user.company_info.company_info

import com.aurimteam.justhobby.response.CourseResponse

class CompanyInfoModel : ICompanyInfoModel {
    interface OnFinishedListener{
        fun onResultSuccess(courses: List<CourseResponse>)
        fun onResultFail(strError: String?)
    }

    override fun getCompanyCoursesData(onFinishedListener: OnFinishedListener) {
        val courses: List<CourseResponse> = listOf(
        /*
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
        */)
        onFinishedListener.onResultSuccess(courses)
    }
}