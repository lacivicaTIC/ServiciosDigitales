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
@Table(name = "tipo_codigo_correo")
public class TipoCodigoCorreo {
    @Id
    @Column(name = "id_tipo_codigo_correo", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 200)
    private String nombre;

    @Column(name = "mensaje", length = 300)
    private String mensaje;

    @Column(name = "asunto", length = 100)
    private String asunto;

}