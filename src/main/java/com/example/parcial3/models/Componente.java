package com.example.parcial3.models;

public record Componente(
        int id,
        String nombreComponente,
        String marca,
        String modelo,
        String descripcion,
        double precio,
        int existencia
) {
}
