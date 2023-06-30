package com.graduate.cheese.domain.user.service.impl

import com.graduate.cheese.domain.image.domain.entity.Image
import com.graduate.cheese.domain.user.presentation.dto.data.GetUserInfoResponseData
import com.graduate.cheese.domain.user.presentation.dto.res.GetUserInfoResponse
import com.graduate.cheese.domain.user.service.GetUserInfoService
import com.graduate.cheese.domain.user.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetUserInfoServiceImpl(
    private val userUtil: UserUtil
) : GetUserInfoService {
    override fun execute(): GetUserInfoResponse {
        val user = userUtil.fetchCurrentUser()
        val imageDayOfMonth = user.image
            .map { it.createdDate.dayOfMonth + it.createdDate.monthValue }
            .distinct()

        val imageList: MutableList<Image> = mutableListOf()
        val responseList: MutableList<GetUserInfoResponseData> = mutableListOf()
        imageDayOfMonth
            .forEach() { day ->
                imageList.clear()
                val filteredImages = user.image.filter { image ->
                    day == image.createdDate.dayOfMonth + image.createdDate.monthValue
                }
                imageList.addAll(filteredImages)
                responseList.add(toData(imageList))
            }

        return GetUserInfoResponse(
            image = responseList
        )
    }

    fun toData(image: MutableList<Image>): GetUserInfoResponseData =
        GetUserInfoResponseData(
            id = image.map { it.id },
            imageUrl = image.map { it.imageUrl },
            day = image[0].createdDate.dayOfMonth
        )
}