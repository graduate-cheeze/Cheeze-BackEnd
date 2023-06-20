package com.graduate.cheese.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {
    PASSWORD_NOT_MATCH("패스워드가 일치하지 않음", 400),
    UNAUTHORIZED("권한이 없음", 401),
    EXPIRED_TOKEN("만료된 토큰", 401),
    USER_NOT_FOUND("존재하지 않는 유저임", 404),
    ROLE_NOT_EXIST("역할이 존재하지 않는 경우", 404),
    ALREADY_EMAIL_EXIST("해당 이메일을 사용하는 유저가 이미 존재함", 409),
    INTERNAL_SERVER_ERROR("서버 내부 에러", 500);
}