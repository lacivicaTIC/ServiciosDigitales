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
@Table(name = "sd_perfiles")
public class SdPerfiles {
    @Id
    @Column(name = "id_perfil", nullable = false)
    private Integer id;

    @Column(name = "perf_nombre", length = 100)
    private String perfNombre;

}