package com.graduate.cheese.domain.auth.service.impl

import com.graduate.cheese.domain.auth.domain.Role
import com.graduate.cheese.domain.auth.domain.entity.RefreshToken
import com.graduate.cheese.domain.auth.domain.repository.RefreshTokenRepository
import com.graduate.cheese.domain.auth.exception.ExistsEmailException
import com.graduate.cheese.domain.auth.presentation.data.dto.SignUpRequestData
import com.graduate.cheese.domain.auth.presentation.data.dto.SignInResponseData
import com.graduate.cheese.domain.auth.service.SignUpService
import com.graduate.cheese.domain.auth.util.AuthConverter
import com.graduate.cheese.domain.user.domain.entity.User
import com.graduate.cheese.domain.user.domain.repository.UserRepository
import com.graduate.cheese.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class SignUpServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
) : SignUpService {
    override fun execute(signUpRequestData: SignUpRequestData): User {
        if (userRepository.existsByEmail(signUpRequestData.email)) throw ExistsEmailException
        return userRepository.save(signUpRequestData.toEntity())
    }

    private fun SignUpRequestData.toEntity(): User =
        User(
            id = UUID.randomUUID(),
            email = email,
            nickName = nickName,
            password = passwordEncoder.encode(password),
            roles = mutableListOf(Role.ROLE_USER)
        )
}