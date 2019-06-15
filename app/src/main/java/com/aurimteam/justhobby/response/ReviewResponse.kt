package com.aurimteam.justhobby.response

class ReviewsResponse(
    val data: List<ReviewResponse>,
    val meta: MetaPagesResponses
)

class ReviewResponse(
    val type: String,
    val user_id: Int,
    val course_id: Int,
    val attributes: ReviewAttrResponse
)

class ReviewAttrResponse(
    val user_image: String?,
    val user_name: String,
    val rating: Int,
    val review: String,
    val created_at: Long,
    val updated_at: Long
)