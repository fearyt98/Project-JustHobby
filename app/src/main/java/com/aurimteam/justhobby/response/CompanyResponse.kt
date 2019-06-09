package com.aurimteam.justhobby.response

class CompanyResponse(
    val type: String,
    val id: Long,
    val attributes: CompanyAttrResponse
)

class CompanyResponseOneR(
    val type: String,
    val id: Long,
    val attributes: CompanyAttrResponse,
    val relationships: CompanyOneRelationshipsResponse
)


class CompanyOneRelationshipsResponse(
    val user: Boolean?,
    val count_reviews: Long,
    val count_courses: Long
)

class CompanyAttrResponse(
    val num_contract: String,

    val is_visible: Boolean,
    val title: String,
    val phone: String,

    val address: String?,
    val fias_id: String?,
    val house: String?,
    val lat: String?,
    val lon: String?,

    val site: String,
    val description: String,
    val rating: String,

    val created_at: Long,
    val updated_at: Long
)