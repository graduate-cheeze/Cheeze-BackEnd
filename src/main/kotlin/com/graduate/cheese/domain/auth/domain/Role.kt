package com.graduate.cheese.domain.auth.domain

import org.springframework.security.core.GrantedAuthority

enum class Role(description: String) : GrantedAuthority {
    ROLE_USER("유저");

    override fun getAuthority(): String = name
}