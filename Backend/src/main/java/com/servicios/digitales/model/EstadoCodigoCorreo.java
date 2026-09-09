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
@Table(name = "estado_codigo_correo")
public class EstadoCodigoCorreo {
    @Id
    @Column(name = "id_estado_codigo", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String nombre;

}