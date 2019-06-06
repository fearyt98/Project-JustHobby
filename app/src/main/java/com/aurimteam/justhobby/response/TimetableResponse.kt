package com.aurimteam.justhobby.response

class TimetableResponse(
    val type: String,
    val group_id: Int,
    val day_week: Int,
    val attributes: TimetableAttrResponse
)

class TimetableAttrResponse(
    val time_start: Int,
    val duration: Int
)