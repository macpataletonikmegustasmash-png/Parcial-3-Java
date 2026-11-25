package com.example.parcial3.repositories;

import com.example.parcial3.models.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepository {

    private final JdbcTemplate jdbc;

    public UsuarioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // El orden debe ser el mismo que el del record Usuario
    private static final RowMapper<Usuario> MAPPER = (rs, fila) -> new Usuario(
            rs.getInt("ID_usuario"),
            rs.getString("nombreUsuario"),
            rs.getString("correoUsuario"),
            rs.getString("contrasenaUsuario"),
            rs.getString("telefonoUsuario"),
            rs.getTimestamp("fechaRegistroUsuario").toLocalDateTime().toLocalDate(),
            rs.getString("estado"),
            rs.getString("nombreRol"));

    private static final String SELECT = """
            SELECT u.ID_usuario, u.nombreUsuario, u.correoUsuario, u.contrasenaUsuario,
                   u.telefonoUsuario, u.fechaRegistroUsuario, u.estado, r.nombreRol
            FROM usuarios u
            JOIN roles r ON r.ID_rol = u.ID_rol
            """;

    public Optional<Usuario> buscarPorId(int id) {
        return jdbc.query(SELECT + " WHERE u.ID_usuario = ?", MAPPER, id).stream().findFirst();
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return jdbc.query(SELECT + " WHERE LOWER(u.correoUsuario) = LOWER(?)", MAPPER, correo).stream().findFirst();
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE LOWER(correoUsuario) = LOWER(?)";
        return jdbc.queryForObject(sql, Integer.class, correo) > 0;
    }

    public boolean existeRol(String rol) {
        String sql = "SELECT COUNT(*) FROM roles WHERE nombreRol = ? AND estadoRol = 'activo'";
        return jdbc.queryForObject(sql, Integer.class, rol) > 0;
    }

    public Usuario crear(String nombre, String correo, String contrasenaHash, String rol) {
        String sql = """
                INSERT INTO usuarios (nombreUsuario, correoUsuario, contrasenaUsuario, ID_rol)
                VALUES (?, ?, ?, (SELECT ID_rol FROM roles WHERE nombreRol = ?))
                RETURNING ID_usuario
                """;
        Integer id = jdbc.queryForObject(sql, Integer.class, nombre, correo, contrasenaHash, rol);
        return buscarPorId(id).orElseThrow();
    }
}