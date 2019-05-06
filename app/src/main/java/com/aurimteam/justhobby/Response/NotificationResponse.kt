package com.aurimteam.justhobby.Response

import java.sql.Timestamp

class NotificationResponse(
    val title: String,
    val description: String,
    val day: Int,
    val month: String,
    val time: Long,
    var new: Boolean
)
