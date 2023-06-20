package com.graduate.cheese.domain.auth.presentation

import com.graduate.cheese.domain.auth.presentation.data.req.SignInRequestDto
import com.graduate.cheese.domain.auth.presentation.data.req.SignUpRequestDto
import com.graduate.cheese.domain.auth.presentation.data.res.SignInResponseDto
import com.graduate.cheese.domain.auth.service.SignInService
import com.graduate.cheese.domain.auth.service.SignUpService
import com.graduate.cheese.domain.auth.util.AuthConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val authConverter: AuthConverter
) {
    @PostMapping
    fun signUp(@RequestBody signUpRequestDto: SignUpRequestDto): ResponseEntity<Void> =
        authConverter.toData(signUpRequestDto)
            .let { signUpService.execute(it) }
            .run { ResponseEntity.ok().build() }

    @PostMapping("signin")
    fun signIn(@RequestBody signInRequestDto: SignInRequestDto): ResponseEntity<SignInResponseDto> =
        authConverter.toData(signInRequestDto)
            .let { signInService.execute(it) }
            .let { ResponseEntity.ok(authConverter.toResponse(it)) }
}