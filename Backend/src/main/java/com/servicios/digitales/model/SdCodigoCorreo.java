package com.servicios.digitales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sd_codigo_correos")
public class SdCodigoCorreo {
    @Id
    @Column(name = "id_codigo_correo", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private com.servicios.digitales.model.SdUsuario usuario;

    @Column(name = "codigo", length = 100)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_codigo_id")
    private com.servicios.digitales.model.TipoCodigoCorreo tipoCodigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_codigo_id")
    private EstadoCodigoCorreo estadoCodigo;

    @Column(name = "correo", length = 200)
    private String correo;

}