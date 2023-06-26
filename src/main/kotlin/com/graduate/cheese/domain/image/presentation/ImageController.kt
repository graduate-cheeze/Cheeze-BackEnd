package com.graduate.cheese.domain.image.presentation

import com.graduate.cheese.domain.image.presentation.dto.res.ImageListResponseDto
import com.graduate.cheese.domain.image.service.UploadImageService
import com.graduate.cheese.domain.image.util.ImageConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageController(
    private val uploadImageService: UploadImageService,
    private val imageConverter: ImageConverter
) {

    @PostMapping
    fun upload(@RequestParam(value = "file") file: List<MultipartFile>): ResponseEntity<ImageListResponseDto> =
        uploadImageService.execute(imageConverter.toData(file))
            .let { imageConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }
}