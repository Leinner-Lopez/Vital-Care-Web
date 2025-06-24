package com.leinner.springboot.vital_care.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "administradores")

public class Administrador extends Usuario{
    
}
