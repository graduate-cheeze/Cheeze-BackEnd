package com.graduate.cheese.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.graduate.cheese.global.error.ErrorCode
import com.graduate.cheese.global.error.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {

    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log.error("======Access Denied======")
        val errorCode = ErrorCode.UNAUTHORIZED
        val responseString = objectMapper.writeValueAsString(ErrorResponse(errorCode.message, errorCode.status))
        response.characterEncoding = "UTF-8"
        response.status = errorCode.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(responseString)
    }
}