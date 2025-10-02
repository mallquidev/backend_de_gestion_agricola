package com.mllq.back.core.domain.core.dto.response.others;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariedadResponse {
    private Long idVariedad;
    private String tipoDeVariedad;
}
