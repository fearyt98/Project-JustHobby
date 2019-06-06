package com.aurimteam.justhobby.response

import java.math.BigInteger

class ReviewResponse(
    val type: String,
    val user_id: BigInteger,
    val course_id: BigInteger,
    val attributes: ReviewAttrResponse
)

class ReviewAttrResponse(
    val rating: Int,
    val review: String,
    val created_at: Long,
    val updated_at: Long
)