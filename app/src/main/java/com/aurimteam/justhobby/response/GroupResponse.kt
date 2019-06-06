package com.aurimteam.justhobby.response

class GroupsResponse(
    val data: List<CategoryResponse>,
    val included: List<CourseResponse>,
    val meta: MetaPagesResponse
)

class GroupResponse(
    val type: String,
    val id: Int,
    val attributes: GroupAttrResponse,
    val relationships: GroupRelationshipsResponse
)

class GroupRelationshipsResponse(
    val course: IdentifierResponse,
    val timetable_near: List<TimetableResponse>
)

class GroupAttrResponse(
    val is_visible: Boolean,
    val status: Boolean,
    val title: String,
    val teacher: String,
    val type_payment: Int,
    val price: Int,
    val sex: List<Int>,
    val age_max: Int,
    val age_min: Int,
    val created_at: Long,
    val updated_at: Long
)