package com.leinner.springboot.vital_care.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leinner.springboot.vital_care.dto.CitaDTO;
import com.leinner.springboot.vital_care.entities.Disponibilidad;
import com.leinner.springboot.vital_care.entities.Medico;
import com.leinner.springboot.vital_care.services.CitaService;
import com.leinner.springboot.vital_care.services.DisponibilidadService;
import com.leinner.springboot.vital_care.services.MedicoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;
    private final CitaService citaService;
    private final DisponibilidadService disponibilidadService;

    @GetMapping
    public String mostrarPaginaPrincipal(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String numeroDocumento = auth.getName(); // El username es el número de documento
        Medico medico = medicoService.obtenerMedicoPorId(Long.valueOf(numeroDocumento));
        model.addAttribute("medico", medico);
        return "Medico/PaginaPrincipal";
    }

    @GetMapping("/disponibilidad")
    public String mostrarPaginaDisponibilidad(Model model) {
        model.addAttribute("disponibilidad", new Disponibilidad());
        model.addAttribute("acción", "/medico/disponibilidad");
        return "Medico/ConfigurarDisponibilidad";
    }

    @PostMapping("/disponibilidad")
    public String postMethodName(@ModelAttribute Disponibilidad disponibilidad) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long numeroDocumento = Long.valueOf(auth.getName());
        disponibilidadService.actualizarDisponibilidad(numeroDocumento, disponibilidad);
        return "redirect:/medico";
    }

    @GetMapping("/citas")
    public String mostrarCitas(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String numeroDocumento = auth.getName(); // El username es el número de documento
        List<CitaDTO> citas = citaService.CitasDocumentoMedico(Long.valueOf(numeroDocumento));
        model.addAttribute("Listar_Citas", citas);
        return "Medico/ListadoCitas";
    }

    @GetMapping("/citas/Eliminar")
    public String eliminarCita(@PathVariable Long Id) {
        citaService.eliminarCita(Id);
        return "redirect:/medico/citas";
    }
}
