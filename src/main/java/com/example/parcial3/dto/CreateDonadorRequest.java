package com.example.parcial3.dto;

public record CreateDonadorRequest(
        String nombre,
        String apellido,
        String tipoDonador,
        String telefono,
        String correo,
        String direccion
) {
}
