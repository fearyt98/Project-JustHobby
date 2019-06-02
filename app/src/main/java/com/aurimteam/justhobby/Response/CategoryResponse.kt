package com.aurimteam.justhobby.Response

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

class CategoriesResponse(
    val data: List<CategoryResponse>,
    val links: LinksPagesResponse,
    val meta: MetaPagesResponse
)

class LinksPagesResponse(
    val first: String?,
    val last: String?,
    val prev: String?,
    val next: String?
)

class MetaPagesResponse(
    val current_page: Int?,
    val from: Int?,
    val last_page: Int?,
    val path: String?,
    val per_page: Int?,
    val to: Int?,
    val total: Int?
)
