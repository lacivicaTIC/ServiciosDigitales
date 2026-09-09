package com.servicios.digitales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sd_personas")
public class SdPersona {
    @Id
    @Column(name = "id_persona", nullable = false)
    private Integer id;

    @Column(name = "per_nombres", length = 200)
    private String perNombres;

    @Column(name = "per_apellidos", length = 200)
    private String perApellidos;

    @Column(name = "per_telefono")
    private String perTelefono;

    @Column(name = "per_direccion", length = 200)
    private String perDireccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_documento_id")
    private com.servicios.digitales.model.TipoDocumento tipoDocumento;

    @Column(name = "per_documento", length = 100)
    private String perDocumento;

    @Column(name = "per_correo", length = 200)
    private String perCorreo;

}