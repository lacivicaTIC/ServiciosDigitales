package com.servicios.digitales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "sd_usuarios")
public class SdUsuario {
    @Id
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Column(name = "usu_nombre", length = 200)
    private String usuNombre;

    @Column(name = "usu_password", length = 200)
    private String usuPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private SdPersona persona;

    @Column(name = "usu_fecha_creacion")
    private LocalDate usuFechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_usuario_id")
    private EstadosUsuario estadoUsuario;

    public EstadosUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

}