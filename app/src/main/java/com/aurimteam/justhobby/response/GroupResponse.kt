package com.aurimteam.justhobby.response

class GroupsResponse(
    val data: List<CategoryResponse>,
    val included: IncludedResponse,
    val meta: MetaPagesResponse
)

class IncludedResponse(
    val subcategories: List<SubcategoryResponse>? = null,
    val courses: List<CourseResponse>? = null,
    val companies: List<CompanyResponse>? = null,
    val groups: List<GroupResponse>? = null

)
class GroupResponse(
    val type: String,
    val id: Long,
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