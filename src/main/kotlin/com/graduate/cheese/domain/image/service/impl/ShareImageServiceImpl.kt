package com.graduate.cheese.domain.image.service.impl

import com.graduate.cheese.domain.image.domain.entity.Image
import com.graduate.cheese.domain.image.domain.repository.ImageRepository
import com.graduate.cheese.domain.image.exception.SizeNotMatchException
import com.graduate.cheese.domain.image.presentation.dto.data.ShareImageRequestData
import com.graduate.cheese.domain.image.service.ShareImageService
import com.graduate.cheese.domain.user.domain.entity.User
import com.graduate.cheese.domain.user.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class ShareImageServiceImpl(
    private val userUtil: UserUtil,
    private val imageRepository: ImageRepository
) : ShareImageService {
    override fun execute(shareImageRequestData: ShareImageRequestData) {
        val user: User = userUtil.fetchCurrentUser()
        if (shareImageRequestData.titles.size == 1) {
            val images: List<Image> = shareImageRequestData.imageUrls.map { value ->
                toEntity(value, shareImageRequestData.titles[0], user)
            }
            imageRepository.saveAll(images)
        } else {
            if (shareImageRequestData.imageUrls.size != shareImageRequestData.titles.size) throw SizeNotMatchException
            val images: List<Image> = shareImageRequestData.imageUrls.mapIndexed() { index, value ->
                toEntity(value, shareImageRequestData.titles[index], user)
            }
            imageRepository.saveAll(images)
        }
    }

    private fun toEntity(imageUrl: String, title: String, user: User): Image =
        Image(
            title = title,
            imageUrl = imageUrl,
            user = user,
            createdDate = LocalDateTime.now()
        )
}