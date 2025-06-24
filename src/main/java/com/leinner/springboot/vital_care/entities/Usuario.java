package com.leinner.springboot.vital_care.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Usuario {

    @Id
    protected Long numeroDocumento;
    protected String nombres;
    protected String apellidos;
    protected String tipoDocumento;
    protected String correo;
    protected String telefono;
    protected String barrio;
    protected String contrasena;
}
