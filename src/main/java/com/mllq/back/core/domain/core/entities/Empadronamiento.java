package com.mllq.back.core.domain.core.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empadronamiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empadronamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empadronamiento")
    private Long idEmpadronamiento;

    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "status")
    private Integer status = 1;
}
