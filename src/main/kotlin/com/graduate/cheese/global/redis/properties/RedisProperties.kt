package com.graduate.cheese.global.redis.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("spring.redis")
data class RedisProperties(
    val host: String,
    val port: Int
)
