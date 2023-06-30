package com.graduate.cheese.domain.user.presentation.dto.res

import com.graduate.cheese.domain.user.presentation.dto.data.GetUserInfoResponseData

data class GetUserInfoResponse(
    val image: List<GetUserInfoResponseData>
)