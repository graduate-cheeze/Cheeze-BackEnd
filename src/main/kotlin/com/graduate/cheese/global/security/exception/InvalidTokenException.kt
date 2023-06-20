package com.graduate.cheese.global.security.exception

import com.graduate.cheese.global.error.ErrorCode
import com.graduate.cheese.global.error.exception.BasicException

class InvalidTokenException : BasicException(ErrorCode.UNAUTHORIZED) {
}