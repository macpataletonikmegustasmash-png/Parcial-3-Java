package com.example.parcial3.services;

import com.example.parcial3.dto.CreateTecnicoRequest;
import com.example.parcial3.models.Tecnico;
import com.example.parcial3.structures.DoublyLinkedList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {

    private final DoublyLinkedList<Tecnico> tecnicos = new DoublyLinkedList<>();

    private int siguienteId = 1;

    public TecnicoService() {
        // (nota de Pat) siguiente++ para que el ID sea ascendente +1
        tecnicos.insertLast(new Tecnico(siguienteId++, "Laura", "Pérez", "Computadoras", "2221234567",
                "laura.perez@reparatec.mx", "Disponible"));
        tecnicos.insertLast(new Tecnico(siguienteId++, "Miguel", "Hernández", "Teléfonos", "2227654321",
                "miguel.hernandez@reparatec.mx", "Ocupado"));
        tecnicos.insertLast(new Tecnico(siguienteId++, "Sofía", "Ramírez", "Tabletas", "2229876543",
                "sofia.ramirez@reparatec.mx", "Disponible"));
    }

    public List<Tecnico> listar() {
        return tecnicos.toList();
    }

    public Tecnico buscarPorId(int id) {
        // (Pat) for-each recorre todos los elementos de una colección sin utilizar índices.
        // tecnico es una variable temporal que representa el elemento actual recorrido
        // tecnicos.toList() es la colección que queremos recorrer
        for (Tecnico tecnico : tecnicos.toList()) {
            // se detendrá hasta encontrar el ID para regresarlo
            if (tecnico.id() == id) {
                return tecnico;
            }
        }
        // si no lo encuentra, no regresa nada
        return null;
    }

    public Tecnico crear(CreateTecnicoRequest datos) {
        Tecnico nuevo = new Tecnico(siguienteId++, datos.nombre(), datos.apellido(), datos.especialidad(),
                datos.telefono(), datos.correo(), "Disponible");
        tecnicos.insertLast(nuevo);
        return nuevo;
    }
}
