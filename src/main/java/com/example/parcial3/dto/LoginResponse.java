package com.example.parcial3.dto;

public record LoginResponse(
        String token,
        UsuarioResponse user) {
}