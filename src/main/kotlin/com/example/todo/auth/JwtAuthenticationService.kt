package com.example.todo.auth

import com.example.todo.models.TokenClaims
import com.nimbusds.jwt.JWTClaimsSet
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtAuthenticationService  {
    fun getClaims(): TokenClaims {
        val authentication = SecurityContextHolder.getContext().authentication
        val details = authentication.details as JWTClaimsSet

        return TokenClaims(
                username = details.getStringClaim("cognito:username"),
                name = details.getStringClaim("name"),
                email = details.getStringClaim("email"),
                phoneNumber = details.getStringClaim("phone_number") ?: "",
                authTime = details.getClaim("auth_time") as Long,
                issued = details.getClaim("iat") as Date,
                expire = details.getClaim("exp") as Date
        )
    }
}
