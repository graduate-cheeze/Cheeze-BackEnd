package com.graduate.cheese.global.security.jwt.data

import java.time.ZonedDateTime

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: ZonedDateTime,
    val refreshExp: ZonedDateTime
)
