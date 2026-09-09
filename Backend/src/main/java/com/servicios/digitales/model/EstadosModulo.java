package com.servicios.digitales.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estados_modulos")
public class EstadosModulo {
    @Id
    @Column(name = "id_estado_modulo", nullable = false)
    private Integer id;

    @Column(name = "est_mod_nombre", length = 100)
    private String estModNombre;

}