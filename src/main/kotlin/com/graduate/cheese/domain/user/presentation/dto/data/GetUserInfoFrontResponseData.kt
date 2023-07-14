package com.graduate.cheese.domain.user.presentation.dto.data

data class GetUserInfoFrontResponseData(
    val id: List<Long>,
    val title: List<String>,
    val imageUrl: List<String>,
    val writer: String,
    val day: Long,
    val createDate: List<String>
)
