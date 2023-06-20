package com.graduate.cheese.domain.auth.service

import com.graduate.cheese.domain.auth.presentation.data.dto.SignUpRequestData
import com.graduate.cheese.domain.user.domain.entity.User

interface SignUpService {
    fun execute(signUpRequestData: SignUpRequestData): User
}