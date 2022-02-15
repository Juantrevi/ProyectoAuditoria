package com.GP.projectApp.Repositorios;

import com.GP.projectApp.Entidades.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaRepositorio extends JpaRepository<HistoriaClinica, Long> {


}
