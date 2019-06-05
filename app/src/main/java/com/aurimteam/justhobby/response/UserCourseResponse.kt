package com.aurimteam.justhobby.response

class UserCourseResponse(
    val title: String,
    val description: String,
    val address: String,
    val tutor: String,
    val timeTable: List<UserCourseTimetableResponse>
)