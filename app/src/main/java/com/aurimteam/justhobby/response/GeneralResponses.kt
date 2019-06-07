package com.aurimteam.justhobby.response

class MetaPagesResponses(
    val current_page: Int?,
    val last_page: Int?,
    val per_page: Int?,
    val total: Int?
)

class IncludedResponse(
    val subcategories: List<SubcategoryResponse>?,
    val courses: List<CourseResponseR>?,
    val companies: List<CompanyResponse>?,
    val groups: List<GroupResponse>?

)
