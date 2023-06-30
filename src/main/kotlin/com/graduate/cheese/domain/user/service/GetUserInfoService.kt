package com.graduate.cheese.domain.user.service

import com.graduate.cheese.domain.user.presentation.dto.res.GetUserInfoResponse

interface GetUserInfoService {
    fun execute(): GetUserInfoResponse
}