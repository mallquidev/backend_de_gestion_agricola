package com.mllq.back.core.domain.core.repo.zonas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mllq.back.core.domain.core.entities.Zonas;

public interface ZonasRespository extends JpaRepository<Zonas, Long>{
    boolean existsByNombreZona(String nombreZona);
}
