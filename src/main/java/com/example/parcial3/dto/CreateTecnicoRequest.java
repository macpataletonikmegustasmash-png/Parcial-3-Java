package com.example.parcial3.dto;

public record CreateTecnicoRequest(
        String nombre,
        String apellido,
        String especialidad,
        String telefono,
        String correo
) {
}
