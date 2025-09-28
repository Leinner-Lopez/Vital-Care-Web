package com.leinner.springboot.vital_care.services.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leinner.springboot.vital_care.dto.MedicoDTO;
import com.leinner.springboot.vital_care.entities.Cita;
import com.leinner.springboot.vital_care.entities.Disponibilidad;
import com.leinner.springboot.vital_care.entities.Medico;
import com.leinner.springboot.vital_care.repository.CitaRepository;
import com.leinner.springboot.vital_care.repository.MedicoRepository;
import com.leinner.springboot.vital_care.services.DisponibilidadService;
import com.leinner.springboot.vital_care.services.MedicoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService{

    private final MedicoRepository medicoRepository;
    private final DisponibilidadService disponibilidadService;
    private final CitaRepository citaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Medico> obtenerMedicos() {
        return medicoRepository.findAll();

    }

    @Override
    public List<MedicoDTO> obtenerMedicosconDisponibilidad(){
        List<Medico> medicos = medicoRepository.findAll();
        List<MedicoDTO> medicosDTO = new ArrayList<>();
        for (Medico medico : medicos){
            Disponibilidad disponibilidad = disponibilidadService.obtenerDisponibilidadPorId(medico.getNumeroDocumento());
            MedicoDTO medicoDTO = new MedicoDTO();
            medicoDTO.setNumeroDocumento(medico.getNumeroDocumento());
            medicoDTO.setNombres(medico.getNombres());
            medicoDTO.setApellidos(medico.getApellidos());
            medicoDTO.setEspecialidad(medico.getEspecialidad());
            medicoDTO.setDisponibilidadFinal(disponibilidad.getFinDisponibilidad());
            medicosDTO.add(medicoDTO);
        }
        return medicosDTO;
    }

    @Override
    public Medico obtenerMedicoPorId(Long numeroDocumento) {
        return medicoRepository.findById(numeroDocumento).orElse(null);
    }

    @Override
    public Medico registrarMedico(Medico medico) {
        medico.setContrasena(passwordEncoder.encode(medico.getContrasena()));
        return medicoRepository.save(medico);
    }

    @Override
    public void eliminarMedico(Long numeroDocumento) {
        List<Cita> citas = citaRepository.findByDocumentoMedico(numeroDocumento);
        citaRepository.deleteAll(citas);
        medicoRepository.deleteById(numeroDocumento);
    }

    @Override
    public Medico actualizarMedico(Long numeroDocumento, Medico medico) {
        Medico medicoExistente = medicoRepository.findById(numeroDocumento).orElse(null);
        if(medicoExistente != null) {
            medicoExistente.setNombres(medico.getNombres());
            medicoExistente.setApellidos(medico.getApellidos());
            medicoExistente.setTipoDocumento(medico.getTipoDocumento());      
            medicoExistente.setCorreo(medico.getCorreo());
            medicoExistente.setTelefono(medico.getTelefono());
            medicoExistente.setBarrio(medico.getBarrio());
            medicoExistente.setEspecialidad(medico.getEspecialidad());
            if(medico.getContrasena() != null && !medico.getContrasena().isEmpty()){
                medicoExistente.setContrasena(passwordEncoder.encode(medico.getContrasena()));
            }
            return medicoRepository.save(medicoExistente);
        }
        return null;
    }

    @Override
    public List<LocalDateTime> obtenerFechasDisponiblesMedico(Long numeroDocumento) {
        // Obtener la disponibilidad del médico
        Disponibilidad disponibilidad = disponibilidadService.obtenerDisponibilidadPorId(numeroDocumento);

        //Obtener las citas ya agendadas para el médico
        List<Cita> citasTomadas = citaRepository.findByDocumentoMedico(numeroDocumento);
        Set<LocalDateTime> fechasTomadas = new HashSet<>();
        for(Cita cita: citasTomadas){
            fechasTomadas.add(cita.getFechaCita());
        }

        //Calcular las fechas disponibles
        List<LocalDateTime> horasDisponibles = new ArrayList<>();
        LocalDateTime inicio = disponibilidad.getInicioDisponibilidad();
        LocalDateTime fin = disponibilidad.getFinDisponibilidad();
        while(!inicio.isAfter(fin)){
            if(!fechasTomadas.contains(inicio)){
                horasDisponibles.add(inicio);
            }
            inicio = inicio.plusMinutes(15);
        }
        return horasDisponibles;
    }
    
}
