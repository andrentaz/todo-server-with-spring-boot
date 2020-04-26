package com.example.todo.models

import java.util.*

data class TokenClaims(val username: String,
                       val name: String,
                       val email: String,
                       val phoneNumber: String,
                       val authTime: Long,
                       val issued: Date,
                       val expire: Date)
