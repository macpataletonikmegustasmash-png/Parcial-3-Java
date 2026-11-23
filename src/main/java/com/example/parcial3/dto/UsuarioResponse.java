package com.example.parcial3.dto;

import com.example.parcial3.models.Usuario;

import java.util.List;

public record UsuarioResponse(int id, String name, String email, String telefono, String role, List<String> roles) {

    public static UsuarioResponse de(Usuario usuario) {
        return new UsuarioResponse(
                usuario.id(),
                usuario.nombreUsuario(),
                usuario.correo(),
                usuario.telefono(),
                usuario.rol(),
                List.of(usuario.rol()));
    }
}