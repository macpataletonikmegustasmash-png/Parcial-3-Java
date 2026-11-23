package com.example.parcial3.services;

import com.example.parcial3.dto.CreateUsuarioRequest;
import com.example.parcial3.models.Usuario;
import com.example.parcial3.security.Roles;
import com.example.parcial3.structures.DoublyLinkedList;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {
    private final DoublyLinkedList<Usuario> usuarios = new DoublyLinkedList<>();
    private int siguienteId = 1;

    public UsuarioService() {
        usuarios.insertLast(new Usuario(siguienteId++, "admin", "admin@reparatec.mx", "",
                "2221002000", LocalDate.of(2026, 9, 1), "Activo", Roles.ADMIN));
        usuarios.insertLast(new Usuario(siguienteId++, "laura.perez", "laura.perez@reparatec.mx", "",
                "2221234567", LocalDate.of(2026, 9, 10), "Activo", Roles.TECNICO));
        usuarios.insertLast(new Usuario(siguienteId++, "techsolutions", "contacto@techsolutions.mx", "",
                "2223456789", LocalDate.of(2026, 10, 2), "Activo", Roles.POR_DEFECTO));
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
        String rol = switch (datos.idRol()) {
            case 1 -> Roles.ADMIN;
            case 2 -> Roles.TECNICO;
            default -> Roles.POR_DEFECTO;
        };
        Usuario nuevo = new Usuario(siguienteId++, datos.nombreUsuario(), datos.correo(), "",
                datos.telefono(), LocalDate.now(), "Activo", rol);
        usuarios.insertLast(nuevo);
        return nuevo;
    }
}