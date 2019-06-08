package com.aurimteam.justhobby.response

class TokenResponse(
    val token: String,
    val user_id: Long,
    val is_full_reg: Boolean
)