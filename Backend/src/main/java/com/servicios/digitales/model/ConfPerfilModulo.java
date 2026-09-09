package com.servicios.digitales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "conf_perfil_modulo")
public class ConfPerfilModulo {
    @Id
    @Column(name = "id_perfil_modulo", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    private com.servicios.digitales.model.SdPerfiles perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id")
    private com.servicios.digitales.model.SdModulo modulo;

}