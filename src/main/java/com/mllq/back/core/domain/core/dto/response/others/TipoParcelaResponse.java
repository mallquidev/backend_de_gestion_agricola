package com.mllq.back.core.domain.core.dto.response.others;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TipoParcelaResponse {
    private Long idParcela;
    private String nombreVariedad;
    private String nombreZona;
    private String tipoParcela;
}
