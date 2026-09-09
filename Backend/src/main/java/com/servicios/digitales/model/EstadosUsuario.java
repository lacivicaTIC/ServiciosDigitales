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
@Table(name = "estados_usuarios")
public class EstadosUsuario {
    @Id
    @Column(name = "id_estado_usuario", nullable = false)
    private Integer id;

    @Column(name = "est_usu_nombre", length = 100)
    private String estUsuNombre;

}