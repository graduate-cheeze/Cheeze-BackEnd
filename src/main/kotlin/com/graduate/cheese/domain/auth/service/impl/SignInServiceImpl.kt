package com.graduate.cheese.domain.auth.service.impl

import com.graduate.cheese.domain.auth.domain.entity.RefreshToken
import com.graduate.cheese.domain.auth.domain.repository.RefreshTokenRepository
import com.graduate.cheese.domain.auth.exception.PasswordNotMatchException
import com.graduate.cheese.domain.auth.presentation.data.dto.SignInRequestData
import com.graduate.cheese.domain.auth.presentation.data.dto.SignInResponseData
import com.graduate.cheese.domain.auth.service.SignInService
import com.graduate.cheese.domain.auth.util.AuthConverter
import com.graduate.cheese.domain.user.domain.repository.UserRepository
import com.graduate.cheese.domain.user.exception.UserNotFoundException
import com.graduate.cheese.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class SignInServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val authConverter: AuthConverter,
    private val jwtTokenProvider: JwtTokenProvider
) : SignInService {
    override fun execute(signInRequestData: SignInRequestData): SignInResponseData {
        val user = userRepository.findByEmail(signInRequestData.email) ?: throw UserNotFoundException

        if (!passwordEncoder.matches(signInRequestData.password, user.password)) throw PasswordNotMatchException

        val (accessToken, refreshToken, accessExp, refreshExp) = jwtTokenProvider.receiveToken(
            email = user.email,
            role = user.roles[0]
        )

        refreshTokenRepository.save(toEntity(user.id, refreshToken))

        return authConverter.toData(accessToken, refreshToken, accessExp, refreshExp)
    }

    private fun toEntity(userId: UUID, refreshToken: String): RefreshToken =
        RefreshToken(
            userId = userId,
            token = refreshToken
        )
}