package com.example.parcial3.controllers;

import com.example.parcial3.dto.CreateTecnicoRequest;
import com.example.parcial3.models.Tecnico;
import com.example.parcial3.services.TecnicoService;
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
@RequestMapping("/api/tecnicos")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    // Inyección de dependencias por constructor
    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping
    public List<Tecnico> listar() {
        return tecnicoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> buscarPorId(@PathVariable int id) {
        Tecnico tecnico = tecnicoService.buscarPorId(id);
        if (tecnico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tecnico);
    }

    @PostMapping
    public ResponseEntity<Tecnico> crear(@RequestBody CreateTecnicoRequest datos) {
        if (datos.nombre() == null || datos.nombre().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Tecnico nuevo = tecnicoService.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}
