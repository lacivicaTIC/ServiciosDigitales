package com.servicios.digitales.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_liquidacion")
public class TipoLiquidacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_liquidacion", nullable = false)
    private Integer id;

    @Size(max = 200)
    @Column(name = "nombre", length = 200)
    private String nombre;

}