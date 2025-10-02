package com.mllq.back.core.domain.core.dto.response.empadronamiento;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpadronamientoResponse {
    private Long idEmpadronamiento;
    private String nombrePersona;
    private String dniPersona;
    private String nombreUsuario;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;
}
