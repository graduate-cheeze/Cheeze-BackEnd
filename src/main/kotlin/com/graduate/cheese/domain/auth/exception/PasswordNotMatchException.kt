package com.graduate.cheese.domain.auth.exception

import com.graduate.cheese.global.error.ErrorCode
import com.graduate.cheese.global.error.exception.BasicException

object PasswordNotMatchException: BasicException(ErrorCode.PASSWORD_NOT_MATCH){
}