package com.graduate.cheese.domain.auth.presentation.data.req

data class SignUpRequestDto(
    val email: String,
    val nickName: String,
    val password: String
)