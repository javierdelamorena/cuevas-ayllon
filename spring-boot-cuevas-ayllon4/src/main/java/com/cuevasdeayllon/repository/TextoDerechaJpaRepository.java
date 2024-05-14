package com.cuevasdeayllon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuevasdeayllon.entity.TextoDerecha;

public interface TextoDerechaJpaRepository extends JpaRepository<TextoDerecha,Integer> {
	
	@Query("select e from TextoDerecha e where e.pagina=?1 ")
	TextoDerecha findByPagina(String pagina);
}
