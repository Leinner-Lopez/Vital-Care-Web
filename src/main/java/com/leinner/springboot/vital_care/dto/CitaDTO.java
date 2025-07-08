package com.leinner.springboot.vital_care.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private Long Id;
    private String nombrePaciente;
    private String nombreMedico;
    private String especialidadMedico;
    private LocalDateTime fechaCita;    
}
