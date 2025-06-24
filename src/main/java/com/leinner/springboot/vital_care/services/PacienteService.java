package com.leinner.springboot.vital_care.services;

import java.util.List;

import com.leinner.springboot.vital_care.entities.Paciente;

public interface PacienteService {
    List<Paciente> obtenerPacientes();
    Paciente obtenerPacientePorId(Long numeroDocumento);
    Paciente registrarPaciente(Paciente paciente);
    void eliminarPaciente(Long numeroDocumento);
    Paciente actualizarPaciente(Long numeroDocumento, Paciente paciente);
}
