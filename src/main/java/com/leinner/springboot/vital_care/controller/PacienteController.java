package com.leinner.springboot.vital_care.controller;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leinner.springboot.vital_care.entities.Medico;
import com.leinner.springboot.vital_care.entities.Paciente;
import com.leinner.springboot.vital_care.services.MedicoService;
import com.leinner.springboot.vital_care.services.PacienteService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/paciente")
@Controller
public class PacienteController {
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    @GetMapping
    public String mostrarPaginaPrincipal(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String numeroDocumento = auth.getName();
        Paciente paciente = pacienteService.obtenerPacientePorId(Long.valueOf(numeroDocumento));
        model.addAttribute("paciente", paciente);
        return "Paciente/PaginaPrincipal";
    }

    @GetMapping("/medicos")
    public String mostrarMedicos(Model model) {
        List<Medico> medicos = medicoService.obtenerMedicos();
        model.addAttribute("Listar_Medicos", medicos);
        return "Paciente/ListadoMedicos";
    }
    
}
