package com.graduate.cheese.domain.image.service

import com.graduate.cheese.domain.image.presentation.dto.data.ShareImageRequestData

interface ShareImageService {
    fun execute(shareImageRequestData: ShareImageRequestData)
}