package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuevasdeayllon.entity.Mercadillo;

public interface MercadilloJpaRepository extends JpaRepository<Mercadillo,Integer>{


	

	@Query("select e from Mercadillo e where id_usuario=?1")
	
	List<Mercadillo> findByIdUsuario(int idUsuario);

	

}
