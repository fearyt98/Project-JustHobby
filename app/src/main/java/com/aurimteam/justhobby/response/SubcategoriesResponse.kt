package com.aurimteam.justhobby.response

class SubcategoryResponse(
    val type: String,
    val id: Int,
    val attributes: SubcategoryAttrResponse
)

class SubcategoriesResponse(
    val data: List<SubcategoryResponse>,
    val meta: MetaPagesResponses
)

class SubcategoryAttrResponse(
    val title: String,
    val slug: String,
    val created_at: Long,
    val updated_at: Long
)