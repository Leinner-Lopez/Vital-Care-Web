package com.leinner.springboot.vital_care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leinner.springboot.vital_care.entities.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>{
    List<Cita> findByDocumentoPaciente(Long numeroDocumento);
    List<Cita> findByDocumentoMedico(Long numeroDocumento);
}
