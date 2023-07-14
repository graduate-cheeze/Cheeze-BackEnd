package com.graduate.cheese.domain.user.service.impl

import com.graduate.cheese.domain.image.domain.entity.Image
import com.graduate.cheese.domain.image.domain.repository.ImageRepository
import com.graduate.cheese.domain.user.domain.entity.User
import com.graduate.cheese.domain.user.presentation.dto.data.GetUserInfoFrontResponseData
import com.graduate.cheese.domain.user.presentation.dto.res.GetUserInfoFrontResponse
import com.graduate.cheese.domain.user.service.GetUserInfoFrontService
import com.graduate.cheese.domain.user.util.UserUtil
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class GetUserInfoFrontServiceImpl(
    private val userUtil: UserUtil,
    private val imageRepository: ImageRepository
) : GetUserInfoFrontService {
    override fun execute(): GetUserInfoFrontResponse {
        val user = userUtil.fetchCurrentUser()
        val image = imageRepository.findAll()
        val imageDayOfMonth = image
            .map { it.createdDate.dayOfMonth + it.createdDate.monthValue }
            .distinct()

        val imageList: MutableList<Image> = mutableListOf()
        val responseList: MutableList<GetUserInfoFrontResponseData> = mutableListOf()
        imageDayOfMonth
            .forEach() { day ->
                imageList.clear()
                val filteredImages = image.filter { image ->
                    day == image.createdDate.dayOfMonth + image.createdDate.monthValue
                }
                imageList.addAll(filteredImages)
                responseList.add(toData(imageList, user))
            }

        return GetUserInfoFrontResponse(
            nickName = user.nickName,
            image = responseList
        )
    }

    fun toData(image: MutableList<Image>, user: User): GetUserInfoFrontResponseData =
        GetUserInfoFrontResponseData(
            id = image.map { it.id },
            title = image.map { it.title },
            imageUrl = image.map { it.imageUrl },
            writer = user.nickName,
            day = ChronoUnit.DAYS.between(image[0].createdDate, LocalDateTime.now()),
            createDate = image.map { it.createdDate.toString() }
        )
}