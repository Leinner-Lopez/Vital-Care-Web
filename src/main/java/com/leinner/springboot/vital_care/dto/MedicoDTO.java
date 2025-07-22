package com.leinner.springboot.vital_care.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicoDTO {
    private Long numeroDocumento;
    private String nombres;
    private String apellidos;
    private String especialidad;
    private LocalDateTime disponibilidadFinal;
}
