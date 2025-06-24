package com.leinner.springboot.vital_care.services.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leinner.springboot.vital_care.entities.Medico;
import com.leinner.springboot.vital_care.repository.MedicoRepository;
import com.leinner.springboot.vital_care.services.MedicoService;

@Service
public class MedicoServiceImpl implements MedicoService{

    @Autowired
    MedicoRepository medicoRepository;

    @Override
    public List<Medico> obtenerMedicos() {
        return medicoRepository.findAll();

    }

    @Override
    public Medico obtenerMedicoPorId(Long numeroDocumento) {
        return medicoRepository.findById(numeroDocumento).orElse(null);
    }

    @Override
    public Medico registrarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public void eliminarMedico(Long numeroDocumento) {
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
            medicoExistente.setContrasena(medico.getContrasena());
            medicoExistente.setEspecialidad(medico.getEspecialidad());
            return medicoRepository.save(medicoExistente);
        }
        return null;
    }
    
}
