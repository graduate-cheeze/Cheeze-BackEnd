package com.graduate.cheese.domain.user.service

import com.graduate.cheese.domain.user.presentation.dto.res.GetUserInfoFrontResponse

interface GetUserInfoFrontService  {
    fun execute(): GetUserInfoFrontResponse

}