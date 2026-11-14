package com.example.parcial3.services;

import com.example.parcial3.dto.CreateSolicitudRequest;
import com.example.parcial3.models.Solicitud;
import com.example.parcial3.structures.DoublyLinkedList;
import com.example.parcial3.structures.LinkedQueue;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SolicitudService {

    private static final String PENDIENTE = "Pendiente";

    private final DoublyLinkedList<Solicitud> solicitudes = new DoublyLinkedList<>();

    private final LinkedQueue<Solicitud> pendientes = new LinkedQueue<>();

    private int siguienteId = 1;

    // Registros de ejemplo: una ya atendida y dos esperando en la cola
    public SolicitudService() {
        solicitudes.insertLast(new Solicitud(siguienteId++, LocalDate.of(2026, 10, 20),
                "No tiene computadora para sus clases", "Clases virtuales", "Alta", "Atendida",
                "Se le asignó una laptop Dell", 1));
        agregar(new Solicitud(siguienteId++, LocalDate.of(2026, 11, 3),
                "Necesita equipo para practicar programación", "Programación", "Media", PENDIENTE, "", 2));
        agregar(new Solicitud(siguienteId++, LocalDate.of(2026, 11, 8),
                "Su teléfono dejó de funcionar", "Actividades administrativas", "Baja", PENDIENTE, "", 3));
    }

    public List<Solicitud> listar() {
        return solicitudes.toList();
    }

    public List<Solicitud> listarPendientes() {
        return pendientes.toList();
    }

    public Solicitud buscarPorId(int id) {
        for (Solicitud solicitud : solicitudes.toList()) {
            if (solicitud.id() == id) {
                return solicitud;
            }
        }
        return null;
    }

    // Toda solicitud nueva entra con la fecha de hoy, como "Pendiente".
    public Solicitud crear(CreateSolicitudRequest datos) {
        Solicitud nueva = new Solicitud(siguienteId++, LocalDate.now(), datos.motivo(), datos.usoDestinado(),
                datos.prioridad(), PENDIENTE, datos.observaciones(), datos.idBeneficiario());
        agregar(nueva);
        return nueva;
    }

    // Saca la primera de la cola (la que lleva más tiempo esperando) y la marca como "Atendida".
    // Regresa null si no hay solicitudes pendientes.
    public Solicitud atenderSiguiente() {
        if (pendientes.isEmpty()) {
            return null;
        }
        Solicitud siguiente = pendientes.dequeue();

        // Un record no se puede modificar: se crea una copia con el estado nuevo
        Solicitud atendida = new Solicitud(siguiente.id(), siguiente.fechaSolicitud(), siguiente.motivo(),
                siguiente.usoDestinado(), siguiente.prioridad(), "Atendida", siguiente.observaciones(),
                siguiente.idBeneficiario());

        // En la lista general se cambia la versión vieja por la atendida
        solicitudes.remove(siguiente);
        solicitudes.insertLast(atendida);
        return atendida;
    }

    private void agregar(Solicitud solicitud) {
        solicitudes.insertLast(solicitud);
        pendientes.enqueue(solicitud);
    }
}
