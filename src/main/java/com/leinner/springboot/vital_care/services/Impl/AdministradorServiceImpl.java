package com.leinner.springboot.vital_care.services.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leinner.springboot.vital_care.entities.Administrador;
import com.leinner.springboot.vital_care.repository.AdministradorRepository;
import com.leinner.springboot.vital_care.services.AdministradorService;

@Service
public class AdministradorServiceImpl implements AdministradorService{
    
    @Autowired
    AdministradorRepository administradorRepository;

    @Override
    public List<Administrador> obtenerAdministradores() {
        return administradorRepository.findAll();

    }

    @Override
    public Administrador obtenerAdministradorPorId(Long numeroDocumento) {
        return administradorRepository.findById(numeroDocumento).orElse(null);
    }

    @Override
    public Administrador registrarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public void eliminarAdministrador(Long numeroDocumento) {
        administradorRepository.deleteById(numeroDocumento);
    }

    @Override
    public Administrador actualizarAdministrador(Long numeroDocumento, Administrador administrador) {
        Administrador adminExistente = administradorRepository.findById(numeroDocumento).orElse(null);
        if(adminExistente != null) {
            adminExistente.setNombres(administrador.getNombres());
            adminExistente.setApellidos(administrador.getApellidos());
            adminExistente.setTipoDocumento(administrador.getTipoDocumento());      
            adminExistente.setCorreo(administrador.getCorreo());
            adminExistente.setTelefono(administrador.getTelefono());
            adminExistente.setBarrio(administrador.getBarrio());
            adminExistente.setContrasena(administrador.getContrasena());
            return administradorRepository.save(adminExistente);
        }
        return null;
    }

}
