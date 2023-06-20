package com.graduate.cheese.global.security.exception

import com.graduate.cheese.global.error.ErrorCode
import com.graduate.cheese.global.error.exception.BasicException

class ExpiredTokenException : BasicException(ErrorCode.EXPIRED_TOKEN)