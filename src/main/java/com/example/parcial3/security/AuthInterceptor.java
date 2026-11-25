package com.example.parcial3.security;

import com.example.parcial3.dto.UsuarioToken;
import com.example.parcial3.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtService jwt;

    public AuthInterceptor(JwtService jwt) {
        this.jwt = jwt;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod metodo)) {
            return true;
        }

        UsuarioToken usuario = jwt.leerToken(request.getHeader("Authorization"));
        request.setAttribute("usuario", usuario);

        RequiereRol requiere = metodo.getMethodAnnotation(RequiereRol.class);
        if (requiere != null && !Arrays.asList(requiere.value()).contains(usuario.role())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para esta acción");
        }
        return true;
    }
}