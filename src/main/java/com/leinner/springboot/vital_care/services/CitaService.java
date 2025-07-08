package com.leinner.springboot.vital_care.services;

import java.util.List;

import com.leinner.springboot.vital_care.dto.CitaDTO;
import com.leinner.springboot.vital_care.entities.Cita;


public interface CitaService {
    Cita agendarCita(Cita cita);
    List<CitaDTO> CitasDocumentoPaciente(Long numeroDocumento);
    List<CitaDTO> CitasDocumentoMedico(Long numeroDocumento);
    void eliminarCita(Long id);
}
