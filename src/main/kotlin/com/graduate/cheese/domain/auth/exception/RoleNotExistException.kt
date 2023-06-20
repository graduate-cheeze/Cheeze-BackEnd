package com.graduate.cheese.domain.auth.exception

import com.graduate.cheese.global.error.ErrorCode
import com.graduate.cheese.global.error.exception.BasicException

class RoleNotExistException : BasicException(ErrorCode.ROLE_NOT_EXIST) {
}