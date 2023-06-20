package com.graduate.cheese.global.error.exception

import com.graduate.cheese.global.error.ErrorCode

open class BasicException(val errorCode: ErrorCode): RuntimeException()