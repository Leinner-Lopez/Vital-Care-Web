package com.leinner.springboot.vital_care.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leinner.springboot.vital_care.dto.CitaDTO;
import com.leinner.springboot.vital_care.entities.Cita;
import com.leinner.springboot.vital_care.entities.Medico;
import com.leinner.springboot.vital_care.entities.Paciente;
import com.leinner.springboot.vital_care.repository.CitaRepository;
import com.leinner.springboot.vital_care.services.CitaService;
import com.leinner.springboot.vital_care.services.MedicoService;
import com.leinner.springboot.vital_care.services.PacienteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CitaServiceImpl implements CitaService{

    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final CitaRepository citaRepository;


    @Override
    public List<CitaDTO> CitasDocumentoPaciente(Long numeroDocumento) {
        List<Cita> citas = citaRepository.findByDocumentoPaciente(numeroDocumento);
        List<CitaDTO> resultado = new ArrayList<>();
        for(Cita cita: citas){
            Paciente paciente = pacienteService.obtenerPacientePorId(cita.getDocumentoPaciente());
            Medico medico = medicoService.obtenerMedicoPorId(cita.getDocumentoMedico());
            CitaDTO citadto = new CitaDTO();
            citadto.setId(cita.getId());
            citadto.setNombrePaciente(paciente.getNombres()+" "+paciente.getApellidos());
            citadto.setNombreMedico(medico.getNombres()+" "+medico.getApellidos());
            citadto.setEspecialidadMedico(medico.getEspecialidad());
            citadto.setFechaCita(cita.getFechaCita());
            resultado.add(citadto);
        }
        return resultado;
    }

    @Override
    public List<CitaDTO> CitasDocumentoMedico(Long numeroDocumento) {
        List<Cita> citas = citaRepository.findByDocumentoMedico(numeroDocumento);
        List<CitaDTO> resultado = new ArrayList<>();
        for(Cita cita: citas){
            Paciente paciente = pacienteService.obtenerPacientePorId(cita.getDocumentoPaciente());
            Medico medico = medicoService.obtenerMedicoPorId(cita.getDocumentoMedico());
            CitaDTO citadto = new CitaDTO();
            citadto.setId(cita.getId());
            citadto.setNombrePaciente(paciente.getNombres()+" "+paciente.getApellidos());
            citadto.setNombreMedico(medico.getNombres()+" "+medico.getApellidos());
            citadto.setEspecialidadMedico(medico.getEspecialidad());
            citadto.setFechaCita(cita.getFechaCita());
            resultado.add(citadto);
        }
        return resultado;
    }

    @Override
    public List<CitaDTO> ListarCitasMedicas() {
        List<Cita> citas = citaRepository.findAll();
        List<CitaDTO> resultado = new ArrayList<>();
        for(Cita cita: citas){
            Paciente paciente = pacienteService.obtenerPacientePorId(cita.getDocumentoPaciente());
            Medico medico = medicoService.obtenerMedicoPorId(cita.getDocumentoMedico());
            CitaDTO citadto = new CitaDTO();
            citadto.setId(cita.getId());
            citadto.setNombrePaciente(paciente.getNombres()+" "+paciente.getApellidos());
            citadto.setNombreMedico(medico.getNombres()+" "+medico.getApellidos());
            citadto.setEspecialidadMedico(medico.getEspecialidad());
            citadto.setFechaCita(cita.getFechaCita());
            resultado.add(citadto);
        }
        return resultado;
    }

    @Override
    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }

    @Override
    public Cita agendarCita(Cita cita) {
        return citaRepository.save(cita);
    }
}
