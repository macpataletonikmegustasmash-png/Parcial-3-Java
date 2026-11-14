package com.example.parcial3.dto;

public record CreateEquipoRequest(
        String codigoInventario,
        String marca,
        String modelo,
        String numeroSerie,
        String procesador,
        String memoriaRam,
        String capacidadAlmacenamiento,
        String sistemaOperativo,
        int idCategoria
) {
}
