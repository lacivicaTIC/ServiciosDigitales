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
@Table(name = "sd_sedes")
public class SdSede {
    @Id
    @Column(name = "id_sede", nullable = false)
    private Integer id;

    @Column(name = "sed_nombre", length = 200)
    private String sedNombre;

    @Column(name = "sed_nombre_corto", length = 100)
    private String sedNombreCorto;

    @Column(name = "sed_nit", length = 100)
    private String sedNit;

    @Column(name = "sed_direccion", length = 200)
    private String sedDireccion;

}