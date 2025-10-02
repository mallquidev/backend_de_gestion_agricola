package com.mllq.back.core.domain.core.dto.request.others;

import lombok.Data;

@Data
public class TipoParcelaRequest {
    private Long idVariedad;
    private Long idZona;
    private String tipoParcela;
}
