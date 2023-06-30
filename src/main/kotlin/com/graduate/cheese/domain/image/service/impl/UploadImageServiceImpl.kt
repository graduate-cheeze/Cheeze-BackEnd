package com.graduate.cheese.domain.image.service.impl

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.graduate.cheese.domain.image.domain.repository.ImageRepository
import com.graduate.cheese.domain.image.exception.FailUploadImageException
import com.graduate.cheese.domain.image.presentation.dto.data.ImageListResponseData
import com.graduate.cheese.domain.image.presentation.dto.data.UploadImageRequestData
import com.graduate.cheese.domain.image.service.UploadImageService
import com.graduate.cheese.domain.user.util.UserUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.*

@Service
class UploadImageServiceImpl(
    private val amazonS3: AmazonS3,
) : UploadImageService {
    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucket: String

    @Value("\${cloud.aws.s3.url}")
    lateinit var url: String
    override fun execute(uploadImageRequestData: UploadImageRequestData): ImageListResponseData {
        val result = mutableListOf<String>()
        uploadImageRequestData.images.forEach {
            val fileName = createFileName(it.originalFilename.toString())
            val objectMetadata = ObjectMetadata()
            objectMetadata.contentLength = it.size
            objectMetadata.contentType = it.contentType

            try {
                it.inputStream.use { inputStream ->
                    amazonS3.putObject(
                        PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
                    )
                }

            } catch (e: IOException) {
                throw FailUploadImageException
            }
            result.add(url + fileName)
        }

        return ImageListResponseData(
            imageUrl = result
        )
    }

    private fun createFileName(fileName: String): String {
        return UUID.randomUUID().toString() + fileName
    }
}