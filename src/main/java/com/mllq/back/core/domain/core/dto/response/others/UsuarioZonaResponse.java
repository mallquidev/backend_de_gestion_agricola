package com.mllq.back.core.domain.core.dto.response.others;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioZonaResponse {
    private Long idUsuariozona;
    private String nombreUsuario;
    private String nombreZona;
}
