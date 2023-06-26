package com.graduate.cheese.domain.image.exception

import com.graduate.cheese.global.error.ErrorCode
import com.graduate.cheese.global.error.exception.BasicException

object FailUploadImageException : BasicException(ErrorCode.INTERNAL_SERVER_ERROR)