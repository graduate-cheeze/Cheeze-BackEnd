package com.graduate.cheese.domain.image.service

import com.graduate.cheese.domain.image.presentation.dto.data.ImageListResponseData
import com.graduate.cheese.domain.image.presentation.dto.data.UploadImageRequestData

interface UploadImageService {
    fun execute(uploadImageRequestData: UploadImageRequestData): ImageListResponseData
}