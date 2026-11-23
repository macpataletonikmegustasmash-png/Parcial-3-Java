package com.example.parcial3.controllers;

import com.example.parcial3.dto.CreateUsuarioRequest;
import com.example.parcial3.dto.UsuarioResponse;
import com.example.parcial3.models.Usuario;
import com.example.parcial3.security.RequiereRol;
import com.example.parcial3.security.Roles;
import com.example.parcial3.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @RequiereRol(Roles.ADMIN)
    public List<UsuarioResponse> listar() {
        return usuarioService.listar().stream().map(UsuarioResponse::de).toList();
    }

    @GetMapping("/{id}")
    @RequiereRol(Roles.ADMIN)
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable int id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UsuarioResponse.de(usuario));
    }

    @PostMapping
    @RequiereRol(Roles.ADMIN)
    public ResponseEntity<UsuarioResponse> crear(@RequestBody CreateUsuarioRequest datos) {
        if (datos.nombreUsuario() == null || datos.nombreUsuario().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Usuario nuevo = usuarioService.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.de(nuevo));
    }
}