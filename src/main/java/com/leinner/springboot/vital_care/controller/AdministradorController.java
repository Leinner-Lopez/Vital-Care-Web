package com.leinner.springboot.vital_care.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.leinner.springboot.vital_care.entities.Administrador;
import com.leinner.springboot.vital_care.entities.Medico;
import com.leinner.springboot.vital_care.entities.Paciente;
import com.leinner.springboot.vital_care.services.AdministradorService;
import com.leinner.springboot.vital_care.services.MedicoService;
import com.leinner.springboot.vital_care.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;



@RequiredArgsConstructor
@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final AdministradorService administradorService;
    
    //Pagina Principal
    @GetMapping
    public String paginaBienvenida(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String numeroDocumento = auth.getName(); // El username es el número de documento
        Administrador administrador = administradorService.obtenerAdministradorPorId(Long.valueOf(numeroDocumento));
        model.addAttribute("administrador", administrador);
        return "Administrador/PaginaPrincipal";
    }


    //Listar Usuarios
    @GetMapping("/pacientes")
    public String listarPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.obtenerPacientes();
        model.addAttribute("Listar_Pacientes", pacientes);
        return "/Administrador/Listado/ListadoPacientes";
    }

    @GetMapping("/medicos")
    public String listarMedicos(Model model) {
        List<Medico> medicos = medicoService.obtenerMedicos();
        model.addAttribute("Listar_Medicos", medicos); 
        return "/Administrador/Listado/ListadoMedicos";
    }

    @GetMapping("/administradores")
    public String listarAdministradores(Model model) {
        List<Administrador> admins = administradorService.obtenerAdministradores();
        model.addAttribute("Listar_Admins", admins);
        return "Administrador/Listado/ListadoAdmins";
    }


    //Formulario de Registro de Usuarios
    @GetMapping("/pacientes/Registrar")
    public String mostrarFormularioRegistroPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("acción", "/administrador/pacientes/Registrar");
        return "/Administrador/Registrar/RegistroPacientes";
    }

    @GetMapping("/medicos/Registrar")
    public String mostrarFormularioRegistroMedicos(Model model) {
        model.addAttribute("medico", new Medico());
        model.addAttribute("acción", "/administrador/medicos/Registrar");
        return "/Administrador/Registrar/RegistroMedicos";
    }
    
    @GetMapping("/administradores/Registrar")
    public String mostrarFormularioRegistroAdministradores(Model model) {
        model.addAttribute("admin", new Administrador());
        model.addAttribute("acción", "/administrador/administradores/Registrar");
        return "/Administrador/Registrar/RegistroAdministrador";
    }
    

    //Registrar Usuario
    @PostMapping("/pacientes/Registrar")
    public String registrarPaciente(@ModelAttribute Paciente paciente, Model model) {
        if(pacienteService.obtenerPacientePorId(paciente.getNumeroDocumento()) != null || medicoService.obtenerMedicoPorId(paciente.getNumeroDocumento()) != null || administradorService.obtenerAdministradorPorId(paciente.getNumeroDocumento()) != null){
            model.addAttribute("error", "El número de documento ya está registrado");
            return "Administrador/Registrar/RegistroPacientes";
        }else{
            pacienteService.registrarPaciente(paciente);
            return "redirect:/administrador/pacientes";
        }
    }

    @PostMapping("/medicos/Registrar")
    public String registrarMedico(@ModelAttribute Medico medico, Model model) {
        if(pacienteService.obtenerPacientePorId(medico.getNumeroDocumento()) != null || medicoService.obtenerMedicoPorId(medico.getNumeroDocumento()) != null || administradorService.obtenerAdministradorPorId(medico.getNumeroDocumento()) != null){
            model.addAttribute("error", "El número de documento ya está registrado");
            return "Administrador/Registrar/RegistroMedicos";
        }else{
            medicoService.registrarMedico(medico);
            return "redirect:/administrador/medicos";
        }
    }

    @PostMapping("/administradores/Registrar")
    public String registrarAdministrador(@ModelAttribute Administrador admin, Model model) {
        if(pacienteService.obtenerPacientePorId(admin.getNumeroDocumento()) != null || medicoService.obtenerMedicoPorId(admin.getNumeroDocumento()) != null || administradorService.obtenerAdministradorPorId(admin.getNumeroDocumento()) != null){
            model.addAttribute("error", "El número de documento ya está registrado");
            return "Administrador/Registrar/RegistroAdministrador";
        }else{
            administradorService.registrarAdministrador(admin);
            return "redirect:/administrador/administradores ";
        }
    }
    
    
    //Mostrar formulario para editar Usuario
    @GetMapping("/pacientes/Actualizar/{numeroDocumento}")
    public String mostrarFormularioEditarPaciente(@PathVariable Long numeroDocumento, @ModelAttribute Paciente paciente, Model model) {
        model.addAttribute("paciente", paciente);
        model.addAttribute("acción", "/administrador/pacientes/Actualizar/"+numeroDocumento);
        return "Administrador/Editar/EditarPacientes";
    }

    @GetMapping("/medicos/Actualizar/{numeroDocumento}")
    public String mostrarFormularioEditarMedico(@PathVariable Long numeroDocumento, @ModelAttribute Medico medico, Model model) {
        model.addAttribute("medico", medico);
        model.addAttribute("acción", "/administrador/medicos/Actualizar/"+numeroDocumento);
        return "Administrador/Editar/EditarMedicos";
    }

    @GetMapping("/administradores/Actualizar/{numeroDocumento}")
    public String mostrarFormularioEditarAdministrador(@PathVariable Long numeroDocumento, @ModelAttribute Administrador admin, Model model) {
        model.addAttribute("admin", admin);
        model.addAttribute("acción", "/administrador/administradores/Actualizar/"+numeroDocumento);
        return "Administrador/Editar/EditarAdministrador";
    }
    
    
    //Actualizar Usuario
    @PostMapping("/pacientes/Actualizar/{numeroDocumento}")
    public String actualizarPaciente(@PathVariable Long numeroDocumento, @ModelAttribute Paciente paciente) {
        pacienteService.actualizarPaciente(numeroDocumento, paciente);
        return "redirect:/administrador/pacientes";
    }

    @PostMapping("/medicos/Actualizar/{numeroDocumento}")
    public String actualizarMedico(@PathVariable Long numeroDocumento, @ModelAttribute Medico medico) {
        medicoService.actualizarMedico(numeroDocumento, medico);
        return "redirect:/administrador/medicos";
    }

    @PostMapping("/administradores/Actualizar/{numeroDocumento}")
    public String actualizarAdministrador(@PathVariable Long numeroDocumento, @ModelAttribute Administrador admin) {
        administradorService.actualizarAdministrador(numeroDocumento, admin);
        return "redirect:/administrador/administradores";
    }
    
    
    //Eliminar Usuario
    @GetMapping("/pacientes/Eliminar/{numeroDocumento}")
    public String eliminarPaciente(@PathVariable Long numeroDocumento) {
        pacienteService.eliminarPaciente(numeroDocumento);
        return "redirect:/administrador/pacientes";
    }

    @GetMapping("/medicos/Eliminar/{numeroDocumento}")
    public String eliminarMedico(@PathVariable Long numeroDocumento) {
        medicoService.eliminarMedico(numeroDocumento);
        return "redirect:/administrador/medicos";
    }

    @GetMapping("/administradores/Eliminar/{numeroDocumento}")
    public String eliminarAdministrador(@PathVariable Long numeroDocumento) {
        administradorService.eliminarAdministrador(numeroDocumento);
        return "redirect:/administrador/administradores";
    }
    
}
