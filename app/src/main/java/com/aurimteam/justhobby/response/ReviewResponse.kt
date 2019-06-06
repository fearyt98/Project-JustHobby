package com.aurimteam.justhobby.response

import java.math.Int

class ReviewResponse(
    val type: String,
    val user_id: Int,
    val course_id: Int,
    val attributes: ReviewAttrResponse
)

class ReviewAttrResponse(
    val rating: Int,
    val review: String,
    val created_at: Long,
    val updated_at: Long
)