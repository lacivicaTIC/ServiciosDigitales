package com.servicios.digitales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sd_sucursal")
public class SdSucursal {
    @Id
    @Column(name = "id_sucursal", nullable = false)
    private Integer id;

    @Column(name = "suc_nombre", length = 200)
    private String sucNombre;

    @Column(name = "suc_direccion", length = 200)
    private String sucDireccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id")
    private SdSede sede;

}