package com.leinner.springboot.vital_care.services;

import com.leinner.springboot.vital_care.entities.Disponibilidad;

public interface  DisponibilidadService {
    Disponibilidad agregarDisponibilidad (Long numeroDocumento);
    Disponibilidad actualizarDisponibilidad (Long numeroDocumento, Disponibilidad disponibilidad);
    Disponibilidad obtenerDisponibilidadPorId (Long numeroDocumento);
}
