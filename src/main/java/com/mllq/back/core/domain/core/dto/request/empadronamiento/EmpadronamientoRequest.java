package com.mllq.back.core.domain.core.dto.request.empadronamiento;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmpadronamientoRequest {
    private Long idPersona;
    private Long idUsuario;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;
}
