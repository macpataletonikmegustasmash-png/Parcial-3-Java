package com.example.parcial3.services;

import com.example.parcial3.dto.CreateDonadorRequest;
import com.example.parcial3.models.Donador;
import com.example.parcial3.structures.DoublyLinkedList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonadorService {
    private final DoublyLinkedList<Donador> donadores = new DoublyLinkedList<>();
    private int siguienteId = 1;

    public DonadorService() {
        donadores.insertLast(new Donador(siguienteId++, "Carlos", "Méndez", "Persona", "2221112233",
                "carlos.mendez@correo.mx", "Calle 5 de Mayo 120, Centro"));
        donadores.insertLast(new Donador(siguienteId++, "Tech Solutions", "S.A. de C.V.", "Empresa", "2223456789",
                "contacto@techsolutions.mx", "Av. Juárez 2500, La Paz"));
        donadores.insertLast(new Donador(siguienteId++, "María", "González", "Persona", "2224445566",
                "maria.gonzalez@correo.mx", "Privada Las Flores 18, San José"));
    }

    public List<Donador> listar() {
        return donadores.toList();
    }

    public Donador buscarPorId(int id) {
        for (Donador donador : donadores.toList()) {
            if (donador.id() == id) {
                return donador;
            }
        }
        return null;
    }

    public Donador crear(CreateDonadorRequest datos) {
        Donador nuevo = new Donador(siguienteId++, datos.nombre(), datos.apellido(), datos.tipoDonador(),
                datos.telefono(), datos.correo(), datos.direccion());
        donadores.insertLast(nuevo);
        return nuevo;
    }
}
