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
@Table(name="rol_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rolusuario")
    private Long roleUserId;

    @Column(name = "id_usuario")
    private Long user;

    @Column(name = "id_rol")
    private Long role;

    @Column(name = "status")
    private Integer status = 1;

}
