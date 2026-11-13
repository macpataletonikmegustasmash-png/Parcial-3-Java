package com.example.parcial3.dto;

// Lo que manda el cliente para registrar un componente (va  sin id).
public record CreateComponenteRequest(
        String nombreComponente,
        String marca,
        String modelo,
        String descripcion,
        double precio,
        int existencia
) {
}
