package com.graduate.cheese.domain.auth.presentation.data.res

import java.time.ZonedDateTime

data class SignInResponseDto(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: ZonedDateTime,
    val refreshExp: ZonedDateTime
)