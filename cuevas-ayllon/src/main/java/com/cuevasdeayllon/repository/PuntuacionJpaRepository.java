package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuevasdeayllon.entity.Puntuacion;

public interface PuntuacionJpaRepository extends JpaRepository<Puntuacion, Integer>{
	
	@Query("select e from Puntuacion e where e.usuario=?1 and e.propuesta=?2 ")
	
	Puntuacion puntuacionDePropuesta(String usuario,String propuesta);
	
	@Query("select e from Puntuacion e where e.propuesta=?1 ")
	
	List<Puntuacion> listaDePuntos(String propuesta);

	void deleteById(int id);
}
