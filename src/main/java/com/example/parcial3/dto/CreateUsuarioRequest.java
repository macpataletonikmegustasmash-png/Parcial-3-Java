package com.example.parcial3.dto;

public record CreateUsuarioRequest(
        String nombreUsuario,
        String correo,
        String telefono,
        int idRol
) {
}
