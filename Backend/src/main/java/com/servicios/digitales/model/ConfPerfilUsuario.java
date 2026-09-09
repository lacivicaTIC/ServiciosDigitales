package com.servicios.digitales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "conf_perfil_usuarios")
public class ConfPerfilUsuario {
    @Id
    @Column(name = "id_conf_perfil_usuario", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private com.servicios.digitales.model.SdUsuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    private com.servicios.digitales.model.SdPerfiles perfil;

    @Column(name = "conf_per_usu_fecha_inicio")
    private LocalDate confPerUsuFechaInicio;

    @Column(name = "conf_per_usu_fecha_fin")
    private LocalDate confPerUsuFechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_creacion_id")
    private com.servicios.digitales.model.SdUsuario usuarioCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_final_id")
    private com.servicios.digitales.model.SdUsuario usuarioFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id")
    private com.servicios.digitales.model.SdSucursal sucursal;

}