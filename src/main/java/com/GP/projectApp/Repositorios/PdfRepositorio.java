package com.GP.projectApp.Repositorios;

import com.GP.projectApp.Entidades.Pdf;
import com.GP.projectApp.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PdfRepositorio extends JpaRepository <Pdf, Long> {

    List<Pdf> findByNombreContaining(String nombre);

    List<Pdf> findByUsuarioContaining(String nombre);

}
