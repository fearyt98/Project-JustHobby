package com.aurimteam.justhobby.response

import java.math.BigInteger

class UserResponse(
    val type: String,
    val id: BigInteger,
    val attributes: UserAttrResponse
)

class UserAttrResponse(
    val first_name: String,
    val last_name: String,
    val full_name: String,
    val address: String?,
    val fias_id: String?,
    val house: String?,
    val lat: String,
    val lon: String,
    val email: String,
    val teacher: String,
    val created_at: Long,
    val updated_at: Long
)