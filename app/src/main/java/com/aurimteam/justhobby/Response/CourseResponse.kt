package com.aurimteam.justhobby.Response

import java.sql.Timestamp

class CourseResponse(
    val type: String,
    val id: Long,
    val title: String,
    val description: String,
    val address: String,
    val company: IdentifierResponse,
    val category: IdentifierResponse,
    val created_at: Timestamp,
    val updated_at: Timestamp
)