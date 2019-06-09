package com.aurimteam.justhobby.response

class GroupsResponseI(
    val data: List<GroupResponse>,
    val included: IncludedResponse,
    val meta: MetaPagesResponses
)

class GroupsResponse(
    val data: List<GroupResponse>,
    val meta: MetaPagesResponses
)

class GroupResponse(
    val type: String,
    val id: Long,
    val attributes: GroupAttrResponse,
    val relationships: GroupRelationshipsResponse
)

class GroupRelationshipsResponse(
    val user: Boolean?,
    val course: IdentifierResponse,
    val timetable: List<TimetableResponse>?,
    val timetable_near: List<TimetableResponse>?
)

class GroupAttrResponse(
    val is_visible: Boolean,
    val status: Boolean,
    val title: String,
    val description: String,
    val teacher: String,
    //val type_payment: Int,
    val price: Int,
    val sex: Int,
    val age_max: Int,
    val age_min: Int,
    val created_at: Long,
    val updated_at: Long
)