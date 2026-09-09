package com.servicios.digitales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "sd_session")
public class SdSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private SdUsuario usuario;

    @Column(name = "sd_fecha_ingreso")
    private LocalDate sdFechaIngreso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_session_id")
    private EstadosSession estadoSession;

    @Column(name = "token", length = 400)
    private String token;

}