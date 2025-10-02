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
@Table(name = "tipo_parcela")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoParcela {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parcela")
    private Long idParcela;

    @Column(name = "id_variedad")
    private Long idVariedad;

    @Column(name = "id_zona")
    private Long idZona;

    @Column(name = "tipo_parcela")
    private String tipoParcela;

    @Column(name = "status")
    private Integer status = 1;
}
