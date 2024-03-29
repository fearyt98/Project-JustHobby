package com.aurimteam.justhobby.response

class CoursesResponse(
    val data: List<CourseResponseR>,
    val included: IncludedResponse,
    val meta: MetaPagesResponses
)

class CourseResponse(
    val type: String,
    val id: Long,
    val attributes: CourseAttrResponse
)

class CourseResponseR(
    val type: String,
    val id: Long,
    val attributes: CourseAttrResponse,
    val relationships: CourseRelationshipsResponse
)

class CourseResponseOneR(
    val type: String,
    val id: Long,
    val attributes: CourseAttrResponse,
    val relationships: CourseOneRelationshipsResponse
)

class CourseOneRelationshipsResponse(
    val user: Boolean?,
    val company: CompanyResponse,
    val count_reviews: Long,
    val count_groups: Long
)

class CourseRelationshipsResponse(
    val user: Boolean?,
    val company: IdentifierResponse
)

class CourseAttrResponse(
    val is_visible: Boolean,
    val title: String,
    val description: String,

    val address: String,
    val length: Int?,
    val lat: String,
    val lon: String,

    val rating: String,

    val image: String?,

    val status: Boolean,
    //val type_payment: Int,
    val price: Int,
    val sex: List<Int>,
    val age_max: Int,
    val age_min: Int,

    val created_at: Long,
    val updated_at: Long
)