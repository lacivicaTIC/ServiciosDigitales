package com.servicios.digitales.security;

import com.servicios.digitales.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivar CSRF con el nuevo método
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v1/rest/api/login/ingresar",
                                "/v1/rest/api/login/registrar",
                                "/v1/rest/api/login/cerrarsession/**",
                                "/v1/rest/api/login/banner",
                                "/v1/rest/api/tipodocumento/listar",
                                "/v1/rest/api/categoriapersona/listar",
                                "/swagger-ui/**",              // swagger UI
                                "/v3/api-docs/**",             // api docs JSON
                                "/swagger-resources/**",       // swagger resources
                                "/webjars/**"
                        ).permitAll() // Usar requestMatchers()
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.disable())
                .cors(cors -> {}); // 👈 importante: activa CORS con tu WebConfig; // Deshabilitar el formulario de login
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }}
