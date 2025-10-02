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
@Table(name = "variedad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Variedad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variedad")
    private Long idVariedad;

    @Column(name = "tipo_de_variedad")
    private String tipoDeVarieadad;

    @Column(name = "status")
    private Integer status = 1;
}
