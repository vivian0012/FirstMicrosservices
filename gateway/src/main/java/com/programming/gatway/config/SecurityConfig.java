package com.programming.gatway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /*
    * JWT (JSON Web Token) é como um bilhete que sua aplicação usa para provar que você está
    * autenticado e autorizado a acessar certas informações. É uma maneira segura de passar informações
    * entre o cliente (por exemplo, o navegador) e o servidor.
    * */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                /*
                * CSRF é um tipo de ataque que engana o navegador do usuário para que ele
                * execute ações não desejadas em uma aplicação web na qual o usuário está autenticado.
                */
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/eureka/**"
                                        /*
                                         * pathMatchers():
                                         * Este método define um matcher para endpoints que começam com /eureka/.
                                         * Ou seja, qualquer requisição que vá para uma URL que comece com /eureka/
                                         * será tratada por esta regra.
                                         * */)
                                .permitAll()
                                .anyExchange()
                                .authenticated()
                /*
                * Authenticated():
                Esta parte da regra indica que todas as requisições que caem nesta regra, ou seja,
                todas as requisições que não são para endpoints /eureka/** exigem que o usuário esteja autenticado.*/)
                .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults())); // Usando Token JWT
        return serverHttpSecurity.build();
    }
}
