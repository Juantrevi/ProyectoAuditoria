package com.GP.projectApp.Entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Pdf implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @OneToOne
    private Usuario usuario;

    public Pdf() {
    }

    public Pdf(String nombre, Usuario usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
