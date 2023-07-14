package com.graduate.cheese.domain.user.presentation.dto.data

import java.time.LocalDateTime

data class GetUserInfoResponseData(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val writer: String,
    val day: Long,
    val createDate: LocalDateTime
)
