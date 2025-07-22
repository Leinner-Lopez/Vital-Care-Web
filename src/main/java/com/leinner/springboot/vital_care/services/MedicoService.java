package com.leinner.springboot.vital_care.services;

import java.time.LocalDateTime;
import java.util.List;

import com.leinner.springboot.vital_care.dto.MedicoDTO;
import com.leinner.springboot.vital_care.entities.Medico;

public interface MedicoService {
    List<Medico> obtenerMedicos();
    List<MedicoDTO> obtenerMedicosconDisponibilidad();
    List<LocalDateTime> obtenerFechasDisponiblesMedico(Long numeroDocumento);
    Medico obtenerMedicoPorId(Long numeroDocumento);
    Medico registrarMedico(Medico medico);
    void eliminarMedico(Long numeroDocumento);
    Medico actualizarMedico(Long numeroDocumento, Medico medio);
}
