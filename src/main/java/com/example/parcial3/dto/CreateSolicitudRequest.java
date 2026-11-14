package com.example.parcial3.dto;

public record CreateSolicitudRequest(
        String motivo,
        String usoDestinado,
        String prioridad,
        String observaciones,
        int idBeneficiario
) {
}
