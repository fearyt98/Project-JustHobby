package com.aurimteam.justhobby.user.main.recommendation_page_viewer.near_user_courses

import com.aurimteam.justhobby.response.*
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.INearCoursesModel
import java.sql.Timestamp

class NearUserCoursesModel : INearCoursesModel {
    interface OnFinishedListener {
        fun onResultSuccess(courses: List<CourseResponse>)
        fun onResultFail()
    }

    override fun getNearCoursesData(onFinishedListener: OnFinishedListener) {
        val courses: List<CourseResponse> = listOf(
            CoursesResponse(
                listOf(
                    CourseResponseR(
                        "course", 16,
                        CourseAttrResponse(
                            true, "Temporibus",
                            "Odit natus ducimus velit amet.",
                            "Громов Street, 4",
                            null,
                            "auayDrZcytdBCyQEaR9NTQV0oW9Q64",
                            "4",
                            "56.4647440000",
                            "84.9665670000",
                            "2.2",
                            true,
                            2,
                            6764,
                            listOf(0, 1),
                            100,
                            0,
                            1558258963,
                            1558258963
                        ),
                        CourseRelationshipsResponse(IdentifierResponse("company_id", 2))
                    )
                ),
                IncludedResponse(
                    null, null, listOf(
                        CompanyResponse(
                            "company",
                            2,
                            CompanyAttrResponse(
                                "CT-02970",
                                true,
                                "МКК ТелекомМорСантех",
                                "77324305560",
                                "Меркушев Street, 44",
                                "XopfRTSoxONhIB8HYnNkYiv2FBBJDh",
                                "44",
                                "56.5053270000",
                                "84.9694710000",
                                "https://ignatov.ru/",
                                "2.68",
                                1558258962,
                                1558258962
                            )
                        )
                    ), null
                ), MetaPagesResponses(null, null, null, null)
            )
        )

        onFinishedListener.onResultSuccess(courses)
    }
}