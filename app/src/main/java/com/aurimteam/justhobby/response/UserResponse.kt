package com.aurimteam.justhobby.response

class UserResponse(
    val type: String,
    val id: Int,
    val attributes: UserAttrResponse
)

class UserAttrResponse(
    val first_name: String,
    val last_name: String,
    val full_name: String,
    val address: String?,
    val lat: String,
    val lon: String,
    val email: String,
    val avatar: String?,
    val teacher: String,
    val created_at: Long,
    val updated_at: Long
)