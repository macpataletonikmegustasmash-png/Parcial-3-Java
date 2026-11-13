package com.example.parcial3.controllers;

import com.example.parcial3.dto.CreateComponenteRequest;
import com.example.parcial3.models.Componente;
import com.example.parcial3.services.ComponenteService;
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
@RequestMapping("/api/componentes")
public class ComponenteController {

    private final ComponenteService componenteService;

    public ComponenteController(ComponenteService componenteService) {
        this.componenteService = componenteService;
    }

    @GetMapping
    public List<Componente> listar() {
        return componenteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Componente> buscarPorId(@PathVariable int id) {
        Componente componente = componenteService.buscarPorId(id);
        if (componente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(componente);
    }

    @PostMapping
    public ResponseEntity<Componente> crear(@RequestBody CreateComponenteRequest datos) {
        if (datos.nombreComponente() == null || datos.nombreComponente().isBlank()
                || datos.precio() < 0 || datos.existencia() < 0) {
            return ResponseEntity.badRequest().build();
        }
        Componente nuevo = componenteService.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}
