package com.example.parcial3.models;

public record Donador(
        int id,
        String nombre,
        String apellido,
        String tipoDonador,
        String telefono,
        String correo,
        String direccion
) {
}
