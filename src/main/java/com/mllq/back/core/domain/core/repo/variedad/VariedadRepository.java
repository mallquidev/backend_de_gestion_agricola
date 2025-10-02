package com.mllq.back.core.domain.core.repo.variedad;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mllq.back.core.domain.core.entities.Variedad;

public interface VariedadRepository extends JpaRepository<Variedad, Long>{
    boolean existsByTipoDeVarieadad(String name);
}
