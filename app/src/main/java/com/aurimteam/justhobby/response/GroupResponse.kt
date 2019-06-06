package com.aurimteam.justhobby.response

class GroupResponse(
    val type: String,
    val id: Int,
    val attributes: GroupAttrResponse
)

class GroupAttrResponse(
    val is_visible: Boolean,
    val status: Boolean,
    val title: String,
    val teacher: String,
    val type_payment: Int,
    val price: Int,
    val sex: List<Int>,
    val age_max: Int,
    val age_min: Int,
    val created_at: Long,
    val updated_at: Long
)