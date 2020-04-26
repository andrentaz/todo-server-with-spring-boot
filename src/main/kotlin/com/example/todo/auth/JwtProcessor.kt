package com.example.todo.auth

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.jwk.source.RemoteJWKSet
import com.nimbusds.jose.proc.JWSVerificationKeySelector
import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jose.util.DefaultResourceRetriever
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import com.nimbusds.jwt.proc.DefaultJWTProcessor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.URL

@Configuration
class JwtProcessor {
    @Value("\${cognito.jwk-set-uri}")
    lateinit var keySource: String

    @Bean
    fun configurableJWTProcessor(): ConfigurableJWTProcessor<*> {
        // JWK Set resource is remote in AWS Cognito
        val resourceRetriever = DefaultResourceRetriever(5000, 5000)
        val jwkSetURL = URL(keySource)
        val keySource: JWKSource<SecurityContext> = RemoteJWKSet(jwkSetURL, resourceRetriever)

        // Setup processor
        val jwtProcessor: ConfigurableJWTProcessor<SecurityContext> = DefaultJWTProcessor()
        val keySelector = JWSVerificationKeySelector(JWSAlgorithm.RS256, keySource)
        jwtProcessor.setJWSKeySelector(keySelector)

        return jwtProcessor
    }
}
