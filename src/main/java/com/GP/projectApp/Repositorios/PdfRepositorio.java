package com.GP.projectApp.Repositorios;

import com.GP.projectApp.Entidades.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepositorio extends JpaRepository <Pdf, Long> {



}
