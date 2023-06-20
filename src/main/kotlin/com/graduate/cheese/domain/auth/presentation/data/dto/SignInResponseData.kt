package com.graduate.cheese.domain.auth.presentation.data.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

data class SignInResponseData(
    val accessToken: String,
    val refreshToken: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val accessExp: ZonedDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val refreshExp: ZonedDateTime
)