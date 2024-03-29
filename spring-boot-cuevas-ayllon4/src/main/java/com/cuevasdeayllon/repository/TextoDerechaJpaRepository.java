package com.cuevasdeayllon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuevasdeayllon.entity.TextoDerecha;

public interface TextoDerechaJpaRepository extends JpaRepository<TextoDerecha,Integer> {
	
	
	TextoDerecha findByPagina(String pagina);
}
