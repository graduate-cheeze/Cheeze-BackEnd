package com.graduate.cheese.domain.user.presentation.dto.res

import com.graduate.cheese.domain.user.presentation.dto.data.GetUserInfoFrontResponseData

data class GetUserInfoFrontResponse(
    val nickName: String,
    val image: List<GetUserInfoFrontResponseData>
)
