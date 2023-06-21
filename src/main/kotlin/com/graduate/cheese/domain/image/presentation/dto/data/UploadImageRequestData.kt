package com.graduate.cheese.domain.image.presentation.dto.data

import org.springframework.web.multipart.MultipartFile

data class UploadImageRequestData(
    val images: List<MultipartFile>
)
