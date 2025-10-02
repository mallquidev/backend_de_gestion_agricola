package com.mllq.back.core.domain.core.dto.response.others;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolUsuarioResponse {
    private long idRolusuario;
    private String nombreUsuario;
    private String nombreRol;
}
