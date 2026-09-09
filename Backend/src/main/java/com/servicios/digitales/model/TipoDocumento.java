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
@Table(name = "tipo_documento")
public class TipoDocumento {
    @Id
    @Column(name = "id_tipo_documento", nullable = false)
    private Integer id;

    @Column(name = "tip_doc_nombre", length = 100)
    private String tipDocNombre;

    @Column(name = "tip_doc_codigo")
    private Integer tipDocCodigo;

    @Column(name = "nombre_corto", length = 100)
    private String nombreCorto;

}