package com.graduate.cheese.global.error.handler

import com.graduate.cheese.global.error.ErrorCode
import com.graduate.cheese.global.error.ErrorResponse
import com.graduate.cheese.global.error.exception.BasicException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    @ExceptionHandler(BasicException::class)
    fun globalExceptionHandler(request: HttpServletRequest, e: BasicException): ResponseEntity<ErrorResponse> {
        log.error(e.errorCode.message)
        log.error(request.requestURI)
        val errorCode: ErrorCode = e.errorCode
        return ResponseEntity(
            ErrorResponse(status = errorCode.status, message = errorCode.message),
            HttpStatus.valueOf(errorCode.status)
        )
    }
}