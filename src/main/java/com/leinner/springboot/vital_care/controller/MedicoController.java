package com.leinner.springboot.vital_care.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.leinner.springboot.vital_care.entities.Medico;
import com.leinner.springboot.vital_care.services.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/medico")
public class MedicoController {
    
    private final MedicoService medicoService;

    @GetMapping
    public String mostrarPaginaPrincipal(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String numeroDocumento = auth.getName(); // El username es el número de documento
        Medico medico = medicoService.obtenerMedicoPorId(Long.valueOf(numeroDocumento));
        model.addAttribute("medico", medico);
        return "Medico/PaginaPrincipal";
    }
    
}
