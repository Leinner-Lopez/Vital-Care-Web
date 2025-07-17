package com.leinner.springboot.vital_care.services.Impl;

import org.springframework.stereotype.Service;

import com.leinner.springboot.vital_care.entities.Disponibilidad;
import com.leinner.springboot.vital_care.repository.DisponibilidadRepository;
import com.leinner.springboot.vital_care.services.DisponibilidadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisponibilidadServiceImpl implements DisponibilidadService{

    private final DisponibilidadRepository disponibilidadRepository; 

    @Override
    public Disponibilidad agregarDisponibilidad(Long numeroDocumento) {
        Disponibilidad disponibilidad = new Disponibilidad();
        disponibilidad.setDocumentoMedico(numeroDocumento);
        return disponibilidadRepository.save(disponibilidad);
    }

    @Override
    public Disponibilidad actualizarDisponibilidad(Long numeroDocumento,Disponibilidad disponibilidad) {
        Disponibilidad disponibilidadExistente = disponibilidadRepository.findById(numeroDocumento).orElse(null);
        if(disponibilidadExistente != null){
            disponibilidadExistente.setInicioDisponibilidad(disponibilidad.getInicioDisponibilidad());
            disponibilidadExistente.setFinDisponibilidad(disponibilidad.getFinDisponibilidad());
            return disponibilidadRepository.save(disponibilidadExistente);
        }
        return null;
    }

    @Override
    public Disponibilidad obtenerDisponibilidadPorId(Long numeroDocumento) {
        return disponibilidadRepository.findById(numeroDocumento).orElse(null);
    }
    
}
