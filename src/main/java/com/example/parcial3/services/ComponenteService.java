package com.example.parcial3.services;

import com.example.parcial3.dto.CreateComponenteRequest;
import com.example.parcial3.models.Componente;
import com.example.parcial3.structures.DoublyLinkedList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponenteService {

    private final DoublyLinkedList<Componente> componentes = new DoublyLinkedList<>();

    private int siguienteId = 1;

    // Registros de ejemplo con los que empieza el sistema
    public ComponenteService() {
        componentes.insertLast(new Componente(siguienteId++, "Memoria RAM 8 GB", "Kingston", "KVR26S19S8/8",
                "DDR4 SO-DIMM para laptop", 450, 12));
        componentes.insertLast(new Componente(siguienteId++, "Disco SSD 240 GB", "Adata", "SU650",
                "SSD SATA de 2.5 pulgadas", 520, 3));
        componentes.insertLast(new Componente(siguienteId++, "Batería para laptop", "Dell", "3RNFD",
                "Batería de 4 celdas, 42 Wh", 890, 2));
    }

    public List<Componente> listar() {
        return componentes.toList();
    }

    public Componente buscarPorId(int id) {
        for (Componente componente : componentes.toList()) {
            if (componente.id() == id) {
                return componente;
            }
        }
        return null;
    }

    public Componente crear(CreateComponenteRequest datos) {
        Componente nuevo = new Componente(siguienteId++, datos.nombreComponente(), datos.marca(), datos.modelo(),
                datos.descripcion(), datos.precio(), datos.existencia());
        componentes.insertLast(nuevo);
        return nuevo;
    }
}
