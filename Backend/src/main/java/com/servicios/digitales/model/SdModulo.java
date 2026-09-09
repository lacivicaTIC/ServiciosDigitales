package com.servicios.digitales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sd_modulos")
public class SdModulo {
    @Id
    @Column(name = "id_modulo", nullable = false)
    private Integer id;

    @Column(name = "mod_nombre", length = 100)
    private String modNombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_modulo_id")
    private EstadosModulo estadoModulo;

    @Column(name = "mod_orden")
    private Integer modOrden;

}