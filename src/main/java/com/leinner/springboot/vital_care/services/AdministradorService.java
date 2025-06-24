package com.leinner.springboot.vital_care.services;

import java.util.List;

import com.leinner.springboot.vital_care.entities.Administrador;

public interface AdministradorService {
    List<Administrador> obtenerAdministradores();
    Administrador obtenerAdministradorPorId(Long numeroDocumento);
    Administrador registrarAdministrador(Administrador administrador);
    void eliminarAdministrador(Long numeroDocumento);
    Administrador actualizarAdministrador(Long numeroDocumento, Administrador administrador);
}
