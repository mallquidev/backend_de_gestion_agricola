package com.mllq.back.core.domain.core.entities;

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
@Table(name = "usuario_zona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioZona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuariozona")
    private Long idUsuarioZona;

    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "idZona")
    private Long idZona;

    @Column(name = "status")
    private Integer status = 1;
}
