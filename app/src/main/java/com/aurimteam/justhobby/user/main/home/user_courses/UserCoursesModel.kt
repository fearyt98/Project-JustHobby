package com.aurimteam.justhobby.user.main.home.user_courses

import com.aurimteam.justhobby.response.UserCourseResponse
import com.aurimteam.justhobby.response.UserCourseTimetableResponse

class UserCoursesModel : IUserCoursesModel {
    interface OnFinishedListener {
        fun onResultSuccess(userCourses: List<UserCourseResponse>)
        fun onResultFail()
    }

    override fun getUserCoursesData(onFinishedListener: OnFinishedListener) {
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