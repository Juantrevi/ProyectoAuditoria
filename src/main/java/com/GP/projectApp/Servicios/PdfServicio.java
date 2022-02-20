package com.GP.projectApp.Servicios;

import com.GP.projectApp.Entidades.Pdf;
import com.GP.projectApp.Repositorios.PdfRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class PdfServicio {

    @Autowired
    private PdfRepositorio pdfRepositorio;

    public List<Pdf> traerTodos(){
        return pdfRepositorio.findAll();
    }

    @Transactional
    public void eliminarHistoria(Long id){
        Optional<Pdf> pdf1 = pdfRepositorio.findById(id);

        String ruta = "C:\\Users\\Juan Manuel\\Desktop\\Proyectos IntelliJ\\ProyectoAuditoria\\src\\main\\resources\\static\\pdf\\";
        File pdf = new File(ruta + pdf1.get().getNombre());
        pdf.delete();
        pdfRepositorio.deleteById(id);
    }


    @Transactional
    public void editarHistoria(Pdf pdf){
        Optional<Pdf> pdf1 = pdfRepositorio.findById(pdf.getId());
        Pdf pdf2 = pdf1.get();

        String ruta = "C:\\Users\\Juan Manuel\\Desktop\\Proyectos IntelliJ\\ProyectoAuditoria\\src\\main\\resources\\static\\pdf\\";
        File file = new File(ruta + pdf1.get().getNombre());
        file.renameTo(new File(ruta + pdf.getNombre()));

        pdf1.get().setNombre(pdf.getNombre());
        pdfRepositorio.save(pdf2);

    }

}
