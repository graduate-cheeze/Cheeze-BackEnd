package com.graduate.cheese.domain.user.exception

import com.graduate.cheese.global.error.ErrorCode
import com.graduate.cheese.global.error.exception.BasicException

object UserNotFoundException : BasicException(ErrorCode.USER_NOT_FOUND)