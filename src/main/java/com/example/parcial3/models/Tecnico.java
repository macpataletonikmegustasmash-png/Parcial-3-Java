package com.example.parcial3.models;

public record Tecnico(
        int id,
        String nombre,
        String apellido,
        String especialidad,
        String telefono,
        String correo,
        String disponibilidad
) {
}
