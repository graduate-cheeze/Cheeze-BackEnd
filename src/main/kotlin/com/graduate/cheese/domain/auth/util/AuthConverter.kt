package com.graduate.cheese.domain.auth.util

import com.graduate.cheese.domain.auth.presentation.data.dto.SignInRequestData
import com.graduate.cheese.domain.auth.presentation.data.dto.SignUpRequestData
import com.graduate.cheese.domain.auth.presentation.data.dto.SignInResponseData
import com.graduate.cheese.domain.auth.presentation.data.req.SignInRequestDto
import com.graduate.cheese.domain.auth.presentation.data.req.SignUpRequestDto
import com.graduate.cheese.domain.auth.presentation.data.res.SignInResponseDto
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class AuthConverter {
    fun toData(signUpRequestDto: SignUpRequestDto): SignUpRequestData =
        SignUpRequestData(
            email = signUpRequestDto.email,
            nickName = signUpRequestDto.nickName,
            password = signUpRequestDto.password
        )

    fun toData(
        accessToken: String,
        refreshToken: String,
        accessExp: ZonedDateTime,
        refreshExp: ZonedDateTime
    ): SignInResponseData =
        SignInResponseData(
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessExp = accessExp,
            refreshExp = refreshExp
        )

    fun toData(signInRequestDto: SignInRequestDto): SignInRequestData =
        SignInRequestData(
            email = signInRequestDto.email,
            password = signInRequestDto.password
        )

    fun toResponse(signInResponseData: SignInResponseData): SignInResponseDto =
        SignInResponseDto(
            accessToken = signInResponseData.accessToken,
            refreshToken = signInResponseData.refreshToken,
            accessExp = signInResponseData.accessExp,
            refreshExp = signInResponseData.refreshExp
        )
}