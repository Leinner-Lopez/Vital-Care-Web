package com.leinner.springboot.vital_care.controller;

import java.time.LocalDateTime;
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
import com.leinner.springboot.vital_care.dto.MedicoDTO;
import com.leinner.springboot.vital_care.entities.Cita;
import com.leinner.springboot.vital_care.entities.Medico;
import com.leinner.springboot.vital_care.entities.Paciente;
import com.leinner.springboot.vital_care.services.CitaService;
import com.leinner.springboot.vital_care.services.MedicoService;
import com.leinner.springboot.vital_care.services.PacienteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/paciente")
@Controller
public class PacienteController {
    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final CitaService citaService;

    @GetMapping
    public String mostrarPaginaPrincipal(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String numeroDocumento = auth.getName();
        Paciente paciente = pacienteService.obtenerPacientePorId(Long.valueOf(numeroDocumento));
        model.addAttribute("paciente", paciente);
        return "Paciente/PaginaPrincipal";
    }

    @GetMapping("/medicos")
    public String mostrarMedicos(Model model) {
        List<MedicoDTO> medicos = medicoService.obtenerMedicosconDisponibilidad();
        model.addAttribute("Listar_Medicos", medicos);
        return "Paciente/ListadoMedicos";
    }

    @GetMapping("/medicos/agendarCita/{numeroDocumento}")
    public String mostrarInterfazAgendarCitaMedica(@PathVariable Long numeroDocumento, Model model) {
        Medico medico = medicoService.obtenerMedicoPorId(numeroDocumento);
        List<LocalDateTime> fechasDisponibles = medicoService.obtenerFechasDisponiblesMedico(numeroDocumento);
        model.addAttribute("medico", medico);
        model.addAttribute("fechaDisponibles", fechasDisponibles);
        model.addAttribute("acción", "/paciente/medicos/agendarCita/" + numeroDocumento);
        model.addAttribute("cita", new Cita());
        return "Paciente/AgendarCita";
    }

    @PostMapping("/medicos/agendarCita/{numeroDocumento}")
    public String agendarCitaMedica(@PathVariable Long numeroDocumento,@ModelAttribute Cita cita) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long numeroDocumentoP = Long.valueOf(auth.getName());
        cita.setDocumentoPaciente(numeroDocumentoP);
        cita.setDocumentoMedico(numeroDocumento);
        citaService.agendarCita(cita);
        return "redirect:/paciente/citas";
    }

    @GetMapping("/citas")
    public String mostrarCitas(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String numeroDocumento = auth.getName();
        List<CitaDTO> citas = citaService.CitasDocumentoPaciente(Long.valueOf(numeroDocumento));
        model.addAttribute("Listar_Citas", citas);
        return "Paciente/ListadoCitas";
    }

    @GetMapping("/citas/Eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return "redirect:/paciente/citas";
    }
}
