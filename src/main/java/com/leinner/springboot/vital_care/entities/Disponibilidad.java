package com.leinner.springboot.vital_care.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Disponibilidad {
    @Id
    private Long documentoMedico;
    @Column(columnDefinition="DATETIME DEFAULT '1999-01-01 00:00:00'")
    private LocalDateTime inicioDisponibilidad;
    @Column(columnDefinition="DATETIME DEFAULT '1999-01-01 00:00:00'")
    private LocalDateTime finDisponibilidad;
}
