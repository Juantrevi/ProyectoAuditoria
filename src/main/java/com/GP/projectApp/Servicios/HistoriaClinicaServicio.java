package com.GP.projectApp.Servicios;

import com.GP.projectApp.Entidades.HistoriaClinica;
import com.GP.projectApp.Entidades.Usuario;
import com.GP.projectApp.Repositorios.HistoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriaClinicaServicio {

    @Autowired
    private HistoriaRepositorio historiaRepositorio;

    public void validar(String nombre, String atencion, String correccion){
        if (nombre.isEmpty()){
            throw new IllegalStateException("El nombre no puede estar vacio");
        }
        if (atencion.isEmpty()){
            throw new IllegalStateException("El numero de atencion no puede estar vacio");
        }
        if (correccion.isEmpty()){
            throw new IllegalStateException("Tiene que haber una correccion a realizar");
        }
    }

    @Transactional
    public void crearHistoriaACorregir(String nombre, String atencion, String correccion, Usuario usuario){

        validar(nombre, atencion, correccion);

        HistoriaClinica historia = new HistoriaClinica();
        historia.setNombre(nombre);
        historia.setAtencion(atencion);
        historia.setCorreccion(correccion);
        historia.setFechaSubida(new Date());
        historia.setCorregida(false);
        historia.setFechaCorreccion(null);
        historia.setUsuarioSubida(usuario);
        historia.setUsuarioCorreccion(null);

        historiaRepositorio.save(historia);
    }

    public List<HistoriaClinica> traerTodas(){
        List<HistoriaClinica> historias = historiaRepositorio.findAll();

        return historias;
    }

    public HistoriaClinica traerHistoria(Long id){
        Optional<HistoriaClinica> historia =historiaRepositorio.findById(id);

        if (historia.isEmpty()){
            throw new IllegalStateException("No existe historia clinica con ese id");
        }
        return historia.get();
    }

    @Transactional
    public void modificar(Long id, String nombre, String atencion, String correccion, Usuario usuario){

        Optional<HistoriaClinica> historia = historiaRepositorio.findById(id);
        HistoriaClinica historiaClinica = historia.get();
        historiaClinica.setNombre(nombre);
        historiaClinica.setAtencion(atencion);
        historiaClinica.setCorreccion(correccion);
        historiaClinica.setUsuarioSubida(usuario);

        historiaRepositorio.save(historiaClinica);
    }

    @Transactional
    public void borrar(Long id){

        HistoriaClinica historiaClinica = historiaRepositorio.getById(id);

        historiaRepositorio.deleteById(id);

    }
}
