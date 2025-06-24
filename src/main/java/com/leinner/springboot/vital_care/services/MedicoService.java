package com.leinner.springboot.vital_care.services;

import java.util.List;

import com.leinner.springboot.vital_care.entities.Medico;

public interface MedicoService {
    List<Medico> obtenerMedicos();
    Medico obtenerMedicoPorId(Long numeroDocumento);
    Medico registrarMedico(Medico medico);
    void eliminarMedico(Long numeroDocumento);
    Medico actualizarMedico(Long numeroDocumento, Medico medio);
}
