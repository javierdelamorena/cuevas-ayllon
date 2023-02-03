package com.cuevasdeayllon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.cuevasdeayllon.entity.Fotos;

public interface FotosJpaRepository extends JpaRepository<Fotos, Integer>{
	
	
	@Query("select e from Fotos  e ")
	Page<Fotos> findAllPage(Pageable page);

}
