package com.mllq.back.core.domain.core.dto.request.persona;

import lombok.Data;

@Data
public class PersonaRequest {
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String dni;
}
