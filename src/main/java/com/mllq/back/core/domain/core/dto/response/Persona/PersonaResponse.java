package com.mllq.back.core.domain.core.dto.response.Persona;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaResponse {
    private Long idPersona;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String dni; 
}
