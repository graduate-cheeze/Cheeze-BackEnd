package com.graduate.cheese.global.security.jwt

import com.graduate.cheese.domain.auth.domain.Role
import com.graduate.cheese.domain.auth.exception.RoleNotExistException
import com.graduate.cheese.global.security.auth.AuthDetailsService
import com.graduate.cheese.global.security.exception.ExpiredTokenException
import com.graduate.cheese.global.security.exception.InvalidTokenException
import com.graduate.cheese.global.security.jwt.data.Token
import com.graduate.cheese.global.security.jwt.properties.JwtProperties
import com.graduate.cheese.global.security.jwt.properties.TokenTimeProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.time.ZonedDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val tokenTimeProperties: TokenTimeProperties,
    private val authDetailsService: AuthDetailsService
) {
    companion object {
        const val ACCESS_TYPE = "access"
        const val REFRESH_TYPE = "refresh"
        const val TOKEN_PREFIX = "Bearer "
        const val AUTHORITY = "authority"
    }

    val accessExpiredTime: ZonedDateTime
        get() = ZonedDateTime.now().plusSeconds(tokenTimeProperties.accessExp)

    val refreshExpiredTime: ZonedDateTime
        get() = ZonedDateTime.now().plusSeconds(tokenTimeProperties.refreshExp)

    fun generateAccessToken(email: String, role: Role): String =
        generateToken(email, ACCESS_TYPE, jwtProperties.accessSecret, tokenTimeProperties.accessExp, role)

    fun generateRefreshToken(email: String, role: Role): String =
        generateToken(email, REFRESH_TYPE, jwtProperties.refreshSecret, tokenTimeProperties.refreshExp, role)

    fun resolveToken(req: HttpServletRequest): String? {
        val token = req.getHeader("Authorization") ?: return null
        return parseToken(token)
    }

    fun receiveToken(email: String, role: Role): Token =
        Token(
            generateAccessToken(email, role),
            generateRefreshToken(email, role),
            accessExpiredTime,
            refreshExpiredTime
        )


    fun exactEmailFromRefreshToken(refresh: String): String {
        return getTokenSubject(refresh, jwtProperties.refreshSecret)
    }

    fun exactRoleFromRefreshToken(refresh: String): Role {
        return when (getTokenBody(refresh, jwtProperties.refreshSecret).get(AUTHORITY, String::class.java)) {
            "ROLE_USER" -> Role.ROLE_USER
            else -> throw RoleNotExistException()
        }

    }

    fun exactTypeFromRefreshToken(refresh: String): String =
        getTokenSubject(refresh, jwtProperties.refreshSecret)

    fun authentication(token: String): Authentication {
        val userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token, jwtProperties.accessSecret))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun parseToken(token: String): String? =
        if (token.startsWith(TOKEN_PREFIX)) token.replace(TOKEN_PREFIX, "") else null

    fun generateToken(email: String, type: String, secret: Key, exp: Long, role: Role): String {
        val claims = Jwts.claims().setSubject(email)
        claims["type"] = type
        claims[AUTHORITY] = role
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .signWith(secret, SignatureAlgorithm.HS256)
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .compact()
    }

    private fun getTokenBody(token: String, secret: Key): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        } catch (e: Exception) {
            throw InvalidTokenException()
        }
    }

    private fun getTokenSubject(token: String, secret: Key): String =
        getTokenBody(token, secret).subject
}