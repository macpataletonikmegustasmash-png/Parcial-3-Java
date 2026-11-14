package com.example.parcial3.services;

import com.example.parcial3.dto.CreateEquipoRequest;
import com.example.parcial3.models.Equipo;
import com.example.parcial3.structures.DoublyLinkedList;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EquipoService {
    private final DoublyLinkedList<Equipo> equipos = new DoublyLinkedList<>();
    private int siguienteId = 1;

    public EquipoService() {
        equipos.insertLast(new Equipo(siguienteId++, "INV-001", "Dell", "Latitude 5480", "DL5480-7781",
                "Intel Core i5-7300U", "8 GB", "256 GB", "Windows 10", LocalDate.of(2026, 10, 1), "Reparado", 1));
        equipos.insertLast(new Equipo(siguienteId++, "INV-002", "HP", "ProBook 440 G5", "HP440-1123",
                "Intel Core i3-8130U", "8 GB", "240 GB", "Windows 11", LocalDate.of(2026, 10, 8), "Reparado", 1));
        equipos.insertLast(new Equipo(siguienteId++, "INV-003", "Samsung", "Galaxy A20", "SMA20-5590",
                "Exynos 7884", "3 GB", "32 GB", "Android 11", LocalDate.of(2026, 10, 15), "En reparación", 3));
    }

    public List<Equipo> listar() {
        return equipos.toList();
    }

    public Equipo buscarPorId(int id) {
        for (Equipo equipo : equipos.toList()) {
            if (equipo.id() == id) {
                return equipo;
            }
        }
        return null;
    }
    public Equipo crear(CreateEquipoRequest datos) {
        Equipo nuevo = new Equipo(siguienteId++, datos.codigoInventario(), datos.marca(), datos.modelo(),
                datos.numeroSerie(), datos.procesador(), datos.memoriaRam(), datos.capacidadAlmacenamiento(),
                datos.sistemaOperativo(), LocalDate.now(), "Recibido", datos.idCategoria());
        equipos.insertLast(nuevo);
        return nuevo;
    }
}
