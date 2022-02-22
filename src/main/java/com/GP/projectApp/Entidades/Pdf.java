package com.GP.projectApp.Entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Pdf implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String usuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;

    public Pdf() {
    }

    public Pdf(String nombre, String usuario, Date creado) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.creado = creado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    @Override
    public String toString() {
        return "Pdf{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", usuario='" + usuario + '\'' +
                ", creado=" + creado +
                '}';
    }
}
