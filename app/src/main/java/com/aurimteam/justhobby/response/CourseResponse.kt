package com.aurimteam.justhobby.response

class CourseResponse(
    val type: String,
    val id: Long,
    val attributes: CourseAttrResponse
)

class CourseAttrResponse(
    val is_visible: Boolean,
    val title: String,
    val description: String,

    val address: String?,
    val fias_id: String?,
    val house: String?,
    val lat: String,
    val lon: String,

    val rating: String,

    val status: Boolean,
    val type_payment: Int,
    val price: Int,
    val sex: List<Int>,
    val age_max: Int,
    val age_min: Int,

    val created_at: Long,
    val updated_at: Long
)

class CoursesResponse(
    val courses: List<CourseResponse>
)
