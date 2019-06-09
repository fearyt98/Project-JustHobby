package com.aurimteam.justhobby.response

class TimelineResponse(
    val data: List<EventResponse>,
    val meta: MetaPagesResponses
)

class EventResponse(
    val type: String,
    val id: Long,
    val attributes: EventAttrResponse
)

class EventAttrResponse(
    val time_start: Int,
    val duration: Int,
    val title_course: String,
    val title_group: String,
    val title_company: String,
    val teacher: String,
    val address: String,
    val category_slug: String
)