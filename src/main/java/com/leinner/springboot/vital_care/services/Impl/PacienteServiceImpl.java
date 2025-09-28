package com.leinner.springboot.vital_care.services.Impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leinner.springboot.vital_care.entities.Cita;
import com.leinner.springboot.vital_care.entities.Paciente;
import com.leinner.springboot.vital_care.repository.CitaRepository;
import com.leinner.springboot.vital_care.repository.PacienteRepository;
import com.leinner.springboot.vital_care.services.PacienteService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    
    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final CitaRepository citaRepository;

    @Override
    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll();

    }

    @Override
    public Paciente obtenerPacientePorId(Long numeroDocumento) {
        return pacienteRepository.findById(numeroDocumento).orElse(null);
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        paciente.setContrasena(passwordEncoder.encode(paciente.getContrasena()));
        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long numeroDocumento) {
        List<Cita> citas = citaRepository.findByDocumentoPaciente(numeroDocumento);
        citaRepository.deleteAll(citas);
        pacienteRepository.deleteById(numeroDocumento);
    }

    @Override
    public Paciente actualizarPaciente(Long numeroDocumento, Paciente paciente) {
        Paciente pacienteExistente = pacienteRepository.findById(numeroDocumento).orElse(null);
        if(pacienteExistente != null) {
            pacienteExistente.setNombres(paciente.getNombres());
            pacienteExistente.setApellidos(paciente.getApellidos());
            pacienteExistente.setTipoDocumento(paciente.getTipoDocumento());       
            pacienteExistente.setCorreo(paciente.getCorreo());
            pacienteExistente.setTelefono(paciente.getTelefono());
            pacienteExistente.setBarrio(paciente.getBarrio());
            pacienteExistente.setSeguroMedico(paciente.getSeguroMedico());
            if(paciente.getContrasena() != null && !paciente.getContrasena().isEmpty()){
                pacienteExistente.setContrasena(passwordEncoder.encode(paciente.getContrasena()));
            }
            return pacienteRepository.save(pacienteExistente);
        }
        return null;
    }
}
