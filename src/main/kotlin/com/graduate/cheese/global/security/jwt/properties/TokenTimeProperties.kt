package com.graduate.cheese.global.security.jwt.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("security")
class TokenTimeProperties(
    val accessExp: Long,
    val refreshExp: Long
)