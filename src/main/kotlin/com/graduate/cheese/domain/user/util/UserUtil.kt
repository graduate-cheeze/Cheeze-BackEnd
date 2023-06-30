package com.graduate.cheese.domain.user.util

import com.graduate.cheese.domain.user.domain.entity.User
import com.graduate.cheese.domain.user.domain.repository.UserRepository
import com.graduate.cheese.domain.user.exception.UserNotFoundException
import com.graduate.cheese.global.security.auth.AuthDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class UserUtil(
    private val userRepository: UserRepository
) {
    fun fetchCurrentUser(): User {
        val principal = SecurityContextHolder.getContext().authentication.principal
        val email = if (principal is AuthDetails) {
            principal.username
        } else {
            principal.toString()
        }
        return fetchUserByEmail(email)
    }
    fun fetchUserByEmail(email: String): User =
        userRepository.findByEmail(email) ?: throw UserNotFoundException
    fun fetchUserEmail(): String {
        val principal = SecurityContextHolder.getContext().authentication.principal
        println(principal)
        println(principal is UserDetails)
        val email = if (principal is UserDetails) {
            (principal as AuthDetails).username
        } else {
            principal.toString()
        }
        return email
    }
}