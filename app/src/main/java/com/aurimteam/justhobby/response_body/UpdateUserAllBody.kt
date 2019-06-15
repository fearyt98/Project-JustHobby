package com.aurimteam.justhobby.response_body

class UpdateUserAllBody(
    val token: String,
    val first_name: String,
    val last_name: String,
    val password_old: String,
    val password: String,
    val password_confirmation: String,
    val address: String?
)