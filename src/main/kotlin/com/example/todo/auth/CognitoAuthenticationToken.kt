package com.example.todo.auth

import com.nimbusds.jwt.JWTClaimsSet
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class CognitoAuthenticationToken(
        private val token: String,
        details: JWTClaimsSet,
        authorities: List<GrantedAuthority> = listOf()
) : AbstractAuthenticationToken(authorities) {
    init {
        setDetails(details)
        isAuthenticated = true
    }
    override fun getCredentials(): Any {
        return token
    }

    override fun getPrincipal(): Any {
        return details
    }
}
