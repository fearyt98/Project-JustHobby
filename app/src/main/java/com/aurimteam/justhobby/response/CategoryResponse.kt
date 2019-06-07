package com.aurimteam.justhobby.response

class CategoriesResponse(
    val data: List<CategoryResponse>,
    val meta: MetaPagesResponses
)

class CategoryResponse(
    val type: String,
    val id: Int,
    val attributes: CategoryAttrResponse
)

class CategoryAttrResponse(
    val slug: String,
    val title: String,
    val created_at: Long,
    val updated_at: Long
)