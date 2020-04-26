package com.example.todo.auth

import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.naming.AuthenticationException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
        val processor: ConfigurableJWTProcessor<SecurityContext>,
        authenticationManager: AuthenticationManager
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            val token = extractToken(request.getHeader("Authorization"))
            val authentication = extractAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
            chain.doFilter(request, response)
        } catch (e: AuthenticationException) {
            response.status = 401
            response.writer.write("Access denied")
        }
    }

    private fun extractToken(header: String?): String? {
        val headers = header?.split("Bearer ")

        return if (headers == null || headers.size < 2) {
            null
        } else {
            headers[1]
        }
    }

    private fun extractAuthentication(token: String?): CognitoAuthenticationToken? {
        if (token == null) {
            return null
        }

        return try {
            val claims = processor.process(token, null)
            CognitoAuthenticationToken(token, claims)
        } catch (e: Exception) {
            throw AuthenticationException("${e.javaClass.simpleName} (${e.message ?: "No message"})")
        }
    }
}
