package com.example.parcial3.models;

import java.time.LocalDate;

public record Equipo(
        int id,
        String codigoInventario,
        String marca,
        String modelo,
        String numeroSerie,
        String procesador,
        String memoriaRam,
        String capacidadAlmacenamiento,
        String sistemaOperativo,
        LocalDate fechaRegistro,
        String estadoEquipo,
        int idCategoria
) {
}
