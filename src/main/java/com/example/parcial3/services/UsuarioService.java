package com.example.parcial3.services;

import com.example.parcial3.dto.CreateUsuarioRequest;
import com.example.parcial3.models.Usuario;
import com.example.parcial3.structures.DoublyLinkedList;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {
    private final DoublyLinkedList<Usuario> usuarios = new DoublyLinkedList<>();
    private int siguienteId = 1;
    public UsuarioService() {
        usuarios.insertLast(new Usuario(siguienteId++, "admin", "admin@reparatec.mx", "2221002000",
                LocalDate.of(2026, 9, 1), "Activo", 1));
        usuarios.insertLast(new Usuario(siguienteId++, "laura.perez", "laura.perez@reparatec.mx", "2221234567",
                LocalDate.of(2026, 9, 10), "Activo", 2));
        usuarios.insertLast(new Usuario(siguienteId++, "techsolutions", "contacto@techsolutions.mx", "2223456789",
                LocalDate.of(2026, 10, 2), "Activo", 3));
    }
    public List<Usuario> listar() {
        return usuarios.toList();
    }
    public Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios.toList()) {
            if (usuario.id() == id) {
                return usuario;
            }
        }
        return null;
    }
    public Usuario crear(CreateUsuarioRequest datos) {
        Usuario nuevo = new Usuario(siguienteId++, datos.nombreUsuario(), datos.correo(), datos.telefono(),
                LocalDate.now(), "Activo", datos.idRol());
        usuarios.insertLast(nuevo);
        return nuevo;
    }
}
