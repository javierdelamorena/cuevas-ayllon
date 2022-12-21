package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuevasdeayllon.entity.Propuestas;

public interface PropuestaJpaRepository extends JpaRepository<Propuestas, Integer>{
	
	
	@Query("select e from Propuestas e where id_Propuesta=?1")
	Propuestas findById(int idPropuesta);
	
	@Query("select e from Propuestas e where propuesta=?1")
	Propuestas findById(String  propuesta);
	
	Propuestas findByTitulo(String titulo);
	
	@Query("select e from Propuestas e where id_usuario=?1")
	List<Propuestas> findById_Uusuario(int id_usuario);

}
