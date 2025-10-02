package com.mllq.back.core.domain.core.repo.persona;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mllq.back.core.domain.core.entities.Persona;


public interface PersonaRepository extends JpaRepository<Persona, Long>{
    Optional<Persona> findByDni(String dni);
    boolean existsByDni(String dni);

    Optional<Persona> findByDniAndStatus(String dni, Integer status);

    List<Persona> findAllByStatus(Integer status);
}
