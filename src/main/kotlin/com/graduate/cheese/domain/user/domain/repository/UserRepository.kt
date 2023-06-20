package com.graduate.cheese.domain.user.domain.repository

import com.graduate.cheese.domain.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID>{
    fun save(user: User): User
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}