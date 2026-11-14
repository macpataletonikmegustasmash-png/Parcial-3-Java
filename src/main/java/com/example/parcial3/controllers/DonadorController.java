package com.example.parcial3.controllers;

import com.example.parcial3.dto.CreateDonadorRequest;
import com.example.parcial3.models.Donador;
import com.example.parcial3.services.DonadorService;
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
@RequestMapping("/api/donadores")
public class DonadorController {

    private final DonadorService donadorService;

    public DonadorController(DonadorService donadorService) {
        this.donadorService = donadorService;
    }

    @GetMapping
    public List<Donador> listar() {
        return donadorService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donador> buscarPorId(@PathVariable int id) {
        Donador donador = donadorService.buscarPorId(id);
        if (donador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(donador);
    }

    @PostMapping
    public ResponseEntity<Donador> crear(@RequestBody CreateDonadorRequest datos) {
        if (datos.nombre() == null || datos.nombre().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Donador nuevo = donadorService.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}