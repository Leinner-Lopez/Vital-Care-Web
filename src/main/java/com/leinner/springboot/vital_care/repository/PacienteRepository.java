package com.leinner.springboot.vital_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.leinner.springboot.vital_care.entities.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
