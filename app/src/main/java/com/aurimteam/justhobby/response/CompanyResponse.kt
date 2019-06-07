package com.aurimteam.justhobby.response

class CompanyResponse(
    val type: String,
    val id: Long,
    val attributes: CompanyAttrResponse
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
    val rating: String,

    val created_at: Long,
    val updated_at: Long
)