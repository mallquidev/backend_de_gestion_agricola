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
@Table(name = "fertilizante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fertilizante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fertilizante")
    private Long idFertilizante;

    @Column(name = "tipo_de_fertilizante")
    private String tipoDeFertilizante;

    @Column(name = "status")
    private Integer status = 1;
}
