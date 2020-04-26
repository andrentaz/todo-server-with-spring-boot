package com.example.todo.controllers

import com.example.todo.auth.JwtAuthenticationService
import com.example.todo.models.TokenClaims
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val jwtAuthenticationService: JwtAuthenticationService) {

    @GetMapping("/whoami")
    fun whoami(): TokenClaims = jwtAuthenticationService.getClaims()
}
