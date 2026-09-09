package com.servicios.digitales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "estados_session")
public class EstadosSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_session", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @OneToMany(mappedBy = "estadoSession")
    private Set<SdSession> civSessions = new LinkedHashSet<>();


    public EstadosSession(Integer id) {
        this.id = id;
    }

    public EstadosSession() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public Set<SdSession> getCivSessions() {return civSessions;}
    public void setCivSessions(Set<SdSession> civSessions) {
        this.civSessions = civSessions;
    }
}