package com.leinner.springboot.vital_care.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.leinner.springboot.vital_care.entities.Paciente;
import com.leinner.springboot.vital_care.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final PacienteService pacienteService;
    
    @GetMapping("/")
    public String mostrarLogin(Model model) {
        return "General/Login";
    }

    @GetMapping("/Registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("acción", "/Registro");
        return "General/RegistroPacientes";
    }  

    @PostMapping("/Registro")
    public String registrarPaciente(@ModelAttribute Paciente paciente, Model model){
        if(pacienteService.obtenerPacientePorId(paciente.getNumeroDocumento()) != null){
            model.addAttribute("error", "El número de documento ya está registrado");
            return "General/RegistroPacientes";
        }else{
            pacienteService.registrarPaciente(paciente);
            return "redirect:/";
        }
    }
}
