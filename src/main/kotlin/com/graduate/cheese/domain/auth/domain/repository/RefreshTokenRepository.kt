package com.graduate.cheese.domain.auth.domain.repository

import com.graduate.cheese.domain.auth.domain.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RefreshTokenRepository : CrudRepository<RefreshToken, UUID> {
}