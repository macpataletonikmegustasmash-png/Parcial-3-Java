package com.example.parcial3.controllers;

import com.example.parcial3.dto.CreateEquipoRequest;
import com.example.parcial3.models.Equipo;
import com.example.parcial3.services.EquipoService;
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
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping
    public List<Equipo> listar() {
        return equipoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> buscarPorId(@PathVariable int id) {
        Equipo equipo = equipoService.buscarPorId(id);
        if (equipo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipo);
    }

    @PostMapping
    public ResponseEntity<Equipo> crear(@RequestBody CreateEquipoRequest datos) {
        if (datos.codigoInventario() == null || datos.codigoInventario().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Equipo nuevo = equipoService.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}
