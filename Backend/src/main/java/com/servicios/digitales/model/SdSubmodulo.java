package com.servicios.digitales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sd_submodulos")
public class SdSubmodulo {
    @Id
    @Column(name = "id_submodulo", nullable = false)
    private Integer id;

    @Column(name = "sub_ruta", length = 200)
    private String subRuta;

    @Column(name = "sub_nombre", length = 100)
    private String subNombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id")
    private SdModulo modulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_modulo_id")
    private EstadosModulo estadoModulo;

}