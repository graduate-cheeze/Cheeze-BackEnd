package com.graduate.cheese.domain.auth.presentation.data.dto

data class SignUpRequestData(
    val email: String,
    val nickName: String,
    val password: String
)
