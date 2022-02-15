package com.GP.projectApp.Entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String atencion;
    private String correccion;
    private Boolean corregida;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fechaSubida;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fechaCorreccion;
    @OneToOne
    private Usuario usuarioSubida;
    @OneToOne
    private Usuario usuarioCorreccion;

    public HistoriaClinica() {
    }

    public HistoriaClinica(String nombre, String atencion, String correccion, Boolean corregida, Date fechaSubida, Date fechaCorreccion, Usuario usuarioSubida, Usuario usuarioCorreccion) {
        this.nombre = nombre;
        this.atencion = atencion;
        this.correccion = correccion;
        this.corregida = corregida;
        this.fechaSubida = fechaSubida;
        this.fechaCorreccion = fechaCorreccion;
        this.usuarioSubida = usuarioSubida;
        this.usuarioCorreccion = usuarioCorreccion;
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

    public String getAtencion() {
        return atencion;
    }

    public void setAtencion(String atencion) {
        this.atencion = atencion;
    }

    public String getCorreccion() {
        return correccion;
    }

    public void setCorreccion(String correccion) {
        this.correccion = correccion;
    }

    public Boolean getCorregida() {
        return corregida;
    }

    public void setCorregida(Boolean corregida) {
        this.corregida = corregida;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public Date getFechaCorreccion() {
        return fechaCorreccion;
    }

    public void setFechaCorreccion(Date fechaCorreccion) {
        this.fechaCorreccion = fechaCorreccion;
    }

    public Usuario getUsuarioSubida() {
        return usuarioSubida;
    }

    public void setUsuarioSubida(Usuario usuarioSubida) {
        this.usuarioSubida = usuarioSubida;
    }

    public Usuario getUsuarioCorreccion() {
        return usuarioCorreccion;
    }

    public void setUsuarioCorreccion(Usuario usuarioCorreccion) {
        this.usuarioCorreccion = usuarioCorreccion;
    }
}
