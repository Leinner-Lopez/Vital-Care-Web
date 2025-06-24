package com.leinner.springboot.vital_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.leinner.springboot.vital_care.entities.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

}
