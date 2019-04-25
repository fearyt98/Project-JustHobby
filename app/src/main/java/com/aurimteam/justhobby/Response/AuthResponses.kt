package com.aurimteam.justhobby.Response

class AuthResponse(
    val token: String,
    val user_id: Long
)

class AuthBody(
    val email: String,
    val password: String
)