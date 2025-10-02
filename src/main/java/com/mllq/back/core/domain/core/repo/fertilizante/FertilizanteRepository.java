package com.mllq.back.core.domain.core.repo.fertilizante;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mllq.back.core.domain.core.entities.Fertilizante;

public interface FertilizanteRepository extends JpaRepository<Fertilizante, Long>{
    boolean existsByTipoDeFertilizante(String tipoDeFertilizante);

}
