package com.graduate.cheese.domain.user.service.impl

import com.graduate.cheese.domain.image.domain.entity.Image
import com.graduate.cheese.domain.image.domain.repository.ImageRepository
import com.graduate.cheese.domain.user.presentation.dto.data.GetUserInfoResponseData
import com.graduate.cheese.domain.user.presentation.dto.res.GetUserInfoResponse
import com.graduate.cheese.domain.user.service.GetUserInfoService
import com.graduate.cheese.domain.user.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
@Transactional(readOnly = true)
class GetUserInfoServiceImpl(
    private val userUtil: UserUtil,
    private val imageRepository: ImageRepository
) : GetUserInfoService {
    override fun execute(): GetUserInfoResponse {
        val user = userUtil.fetchCurrentUser()
        val image = imageRepository.findAll()
        val imageList = image.map { toData(it) }

        return GetUserInfoResponse(
            nickName = user.nickName,
            image = imageList
        )
    }

    fun toData(image: Image): GetUserInfoResponseData =
        GetUserInfoResponseData(
            id = image.id,
            imageUrl = image.imageUrl,
            day = ChronoUnit.DAYS.between(image.createdDate, LocalDateTime.now()),
            writer = image.user.nickName,
            title = image.title,
            createDate = image.createdDate.toString()
        )
}