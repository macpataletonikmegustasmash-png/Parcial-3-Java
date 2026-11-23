package com.example.parcial3.models;

import java.time.LocalDate;

public record Usuario(
        int id,
        String nombreUsuario,
        String correo,
        String contrasenaUsuario,
        String telefono,
        LocalDate fechaRegistro,
        String estado,
        String rol
) {
}