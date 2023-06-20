package com.graduate.cheese.domain.auth.service

import com.graduate.cheese.domain.auth.presentation.data.dto.SignInRequestData
import com.graduate.cheese.domain.auth.presentation.data.dto.SignInResponseData

interface SignInService {
    fun execute(signInRequestData: SignInRequestData): SignInResponseData
}