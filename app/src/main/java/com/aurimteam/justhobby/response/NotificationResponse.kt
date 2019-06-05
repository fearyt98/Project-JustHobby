package com.aurimteam.justhobby.response

class NotificationResponse(
    val title: String,
    val description: String,
    val day: Int,
    val month: String,
    val time: Long,
    var new: Boolean
)
