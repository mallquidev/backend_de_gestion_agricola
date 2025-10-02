package com.mllq.back.core.domain.core.dto.response.others;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuariosResponse {
    private Long idUsuario;
    private String correo;
}
