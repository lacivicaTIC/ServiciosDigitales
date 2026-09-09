package com.servicios.digitales.filters;


import com.servicios.digitales.repository.ISdSessionRepository;
import com.servicios.digitales.security.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.servicios.digitales.model.EstadosSession;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ISdSessionRepository sessionRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Ignorar el filtro en la ruta de login
        String path = request.getRequestURI();
        return path.startsWith("/v1/rest/api/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    if (sessionRepository.existsByTokenAndEstadoSession(token,new EstadosSession( 1))){
                        break; // Salir del bucle si se encuentra el token válido
                    } else {
                        token = null;
                        // ❌ Token no válido → devolver UNAUTHORIZED
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"Inicio de sesion registrado en otra pestaña\"}");
                        return; // 🚨 Importante: detener el filtro aquí
                    }
                }
            }
        }

        if (token != null ) {
            try {

                String username = jwtUtils.validateToken(token);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // ✅ Esto habilita al usuario como autenticado
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                System.out.println("Token inválido: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}