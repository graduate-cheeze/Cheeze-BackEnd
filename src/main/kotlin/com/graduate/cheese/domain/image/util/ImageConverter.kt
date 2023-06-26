package com.graduate.cheese.domain.image.util

import com.graduate.cheese.domain.image.presentation.dto.data.ImageListResponseData
import com.graduate.cheese.domain.image.presentation.dto.data.UploadImageRequestData
import com.graduate.cheese.domain.image.presentation.dto.res.ImageListResponseDto
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class ImageConverter {
    fun toData(file: List<MultipartFile>): UploadImageRequestData =
        UploadImageRequestData(
            images = file
        )

    fun toResponse(imageListResponseData: ImageListResponseData): ImageListResponseDto =
        ImageListResponseDto(
            imageUrl = imageListResponseData.imageUrl
        )
}