package com.cuevasdeayllon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuevasdeayllon.entity.PuntuacionTotal;

public interface PuntuacionTotalJpaRepository extends JpaRepository<PuntuacionTotal,Integer> {

	@Query("select e from PuntuacionTotal e where e.propuesta=?1")

	PuntuacionTotal findByPropuesta(String propuesta);
	
	

}
