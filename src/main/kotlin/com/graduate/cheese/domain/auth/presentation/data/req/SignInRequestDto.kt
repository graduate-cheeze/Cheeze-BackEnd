package com.graduate.cheese.domain.auth.presentation.data.req

data class SignInRequestDto(
    val email: String,
    val password: String
)