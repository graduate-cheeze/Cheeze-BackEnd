package com.graduate.cheese.domain.user.presentation

import com.graduate.cheese.domain.user.service.GetUserInfoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController(
    private val getUserInfoService: GetUserInfoService
){
    @GetMapping
    fun getUserInfo() = getUserInfoService.execute()

}