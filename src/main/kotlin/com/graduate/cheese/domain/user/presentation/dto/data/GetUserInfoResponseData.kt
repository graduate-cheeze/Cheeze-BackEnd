package com.graduate.cheese.domain.user.presentation.dto.data

data class GetUserInfoResponseData(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val writer: String,
    val day: Long
)
