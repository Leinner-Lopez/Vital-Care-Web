package com.leinner.springboot.vital_care.services.Impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.leinner.springboot.vital_care.entities.Administrador;
import com.leinner.springboot.vital_care.entities.Medico;
import com.leinner.springboot.vital_care.entities.Paciente;
import com.leinner.springboot.vital_care.services.AdministradorService;
import com.leinner.springboot.vital_care.services.MedicoService;
import com.leinner.springboot.vital_care.services.PacienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements UserDetailsService{
    
    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final AdministradorService administradorService;

    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException{
        Paciente paciente = pacienteService.obtenerPacientePorId(Long.valueOf(Username));
        if (paciente != null) {
            return User.withUsername(Username)
            .password(paciente.getContrasena())
            .roles("PACIENTE")
            .build();
        }
        Medico medico = medicoService.obtenerMedicoPorId(Long.valueOf(Username));
        if (medico != null) {
            return User.withUsername(Username)
            .password(medico.getContrasena())
            .roles("MEDICO")
            .build();
        }
        Administrador administrador = administradorService.obtenerAdministradorPorId(Long.valueOf(Username));
        if (administrador != null) {
            return User.withUsername(Username)
            .password(administrador.getContrasena())
            .roles("ADMIN")
            .build();
        }
        throw new UsernameNotFoundException("Usuario no encontrado");
    }
}
