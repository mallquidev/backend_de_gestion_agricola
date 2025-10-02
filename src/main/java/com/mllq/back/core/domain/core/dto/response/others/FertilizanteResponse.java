package com.mllq.back.core.domain.core.dto.response.others;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FertilizanteResponse {
    private Long idFertilizante;
    private String tipoDeFertilizante;
}
