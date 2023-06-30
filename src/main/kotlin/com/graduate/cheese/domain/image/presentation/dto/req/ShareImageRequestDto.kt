package com.graduate.cheese.domain.image.presentation.dto.req

data class ShareImageRequestDto(
    val titles: List<String>,
    val imageUrls: List<String>
)
