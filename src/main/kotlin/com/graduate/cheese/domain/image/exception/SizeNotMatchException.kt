package com.graduate.cheese.domain.image.exception

import com.graduate.cheese.global.error.ErrorCode
import com.graduate.cheese.global.error.exception.BasicException

object SizeNotMatchException : BasicException(ErrorCode.SIZE_NOT_MATCH)