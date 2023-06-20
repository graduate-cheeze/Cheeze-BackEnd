package com.graduate.cheese.domain.auth.exception

import com.graduate.cheese.global.error.ErrorCode
import com.graduate.cheese.global.error.exception.BasicException

object ExistsEmailException : BasicException(ErrorCode.ALREADY_EMAIL_EXIST)