package com.example.parcial3.models;

import java.time.LocalDate;

public record Solicitud(
        int id,
        LocalDate fechaSolicitud,
        String motivo,
        String usoDestinado,
        String prioridad,
        String estado,
        String observaciones,
        int idBeneficiario
) {
}
