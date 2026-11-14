package com.example.parcial3.controllers;

import com.example.parcial3.dto.CreateSolicitudRequest;
import com.example.parcial3.models.Solicitud;
import com.example.parcial3.services.SolicitudService;
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
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping
    public List<Solicitud> listar() {
        return solicitudService.listar();
    }

    @GetMapping("/pendientes")
    public List<Solicitud> listarPendientes() {
        return solicitudService.listarPendientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> buscarPorId(@PathVariable int id) {
        Solicitud solicitud = solicitudService.buscarPorId(id);
        if (solicitud == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(solicitud);
    }

    @PostMapping
    public ResponseEntity<Solicitud> crear(@RequestBody CreateSolicitudRequest datos) {
        if (datos.motivo() == null || datos.motivo().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Solicitud nueva = solicitudService.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PostMapping("/atender")
    public ResponseEntity<Solicitud> atenderSiguiente() {
        Solicitud atendida = solicitudService.atenderSiguiente();
        if (atendida == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atendida);
    }
}
