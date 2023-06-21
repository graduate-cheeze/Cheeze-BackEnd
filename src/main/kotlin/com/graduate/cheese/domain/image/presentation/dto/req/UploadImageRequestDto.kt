package com.graduate.cheese.domain.image.presentation.dto.req

import org.springframework.web.multipart.MultipartFile

data class UploadImageRequestDto(
    val images: List<MultipartFile>
)