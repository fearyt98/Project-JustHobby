package com.aurimteam.justhobby.Main.Home.UserCourses

import com.aurimteam.justhobby.Response.UserCourseResponse
import com.aurimteam.justhobby.Response.UserCourseTimetableResponse

class UserCoursesModel : IUserCoursesModel {
    interface onFinishedListener {
        fun onResultSuccess(userCourses: List<UserCourseResponse>)
        fun onResultFail()
    }

    override fun getUserCoursesData(onFinishedListener: onFinishedListener) {
        val userCourses: List<UserCourseResponse> = listOf(
            UserCourseResponse(
                "Вотосные единоборства",
                "Drive - школа единоборств",
                "пр. Комсомольский 140",
                "Мария Алексеевна",
                listOf(
                    UserCourseTimetableResponse(
                        "ПН",
                        "10:00",
                        "20:00"
                    ),
                    UserCourseTimetableResponse(
                        "СБ",
                        "10:00",
                        "20:00"
                    ),
                    UserCourseTimetableResponse(
                        "ВС",
                        "10:00",
                        "20:00"
                    )
                )
            ),
            UserCourseResponse(
                "Вотосные единоборства",
                "Drive - школа единоборств",
                "пр. Комсомольский 140",
                "Мария Алексеевна",
                listOf(
                    UserCourseTimetableResponse(
                        "ВТ",
                        "10:00",
                        "20:00"
                    ),
                    UserCourseTimetableResponse(
                        "ЧТ",
                        "9:00",
                        "20:00"
                    ),
                    UserCourseTimetableResponse(
                        "ВС",
                        "10:00",
                        "20:00"
                    )
                )
            ),
            UserCourseResponse(
                "Вотосные единоборства",
                "Drive - школа единоборств",
                "пр. Комсомольский 140",
                "Мария Алексеевна",
                listOf(
                    UserCourseTimetableResponse(
                        "СР",
                        "10:00",
                        "20:00"
                    ),
                    UserCourseTimetableResponse(
                        "ПН",
                        "5:00",
                        "20:00"
                    ),
                    UserCourseTimetableResponse(
                        "ВС",
                        "10:00",
                        "20:00"
                    )
                )
            ),
            UserCourseResponse(
                "Вотосные единоборства",
                "Drive - школа единоборств",
                "пр. Комсомольский 140",
                "Мария Алексеевна",
                listOf(
                    UserCourseTimetableResponse(
                        "ЧТ",
                        "10:00",
                        "20:00"
                    ),
                    UserCourseTimetableResponse(
                        "ВТ",
                        "10:00",
                        "20:00"
                    ),
                    UserCourseTimetableResponse(
                        "ВС",
                        "10:00",
                        "20:00"
                    )
                )
            )
        )
        onFinishedListener.onResultSuccess(userCourses)
    }
}