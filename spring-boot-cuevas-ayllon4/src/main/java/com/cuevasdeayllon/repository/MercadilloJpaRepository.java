package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuevasdeayllon.entity.Mercadillo;

public interface MercadilloJpaRepository extends JpaRepository<Mercadillo,Integer>{


	

	@Query("select e from Mercadillo e where id_usuario=?1")

	List<Mercadillo> findByIdUsuario(int idUsuario);

	@Query("select e from Mercadillo e where tipo_servicio=?1")

	List<Mercadillo> findByTipo_servicio(String tipo_servicio);
	
	
	
	@Query("select e from Mercadillo  e ")
	
	Page<Mercadillo> findAllPageMercadillo(Pageable page);
	
	@Query("select e from Mercadillo e where tipo_servicio=?1")

	Page<Mercadillo> findPaginaByTipo_servicio(Pageable page,String tipo_servicio);
	@Query("select e from Mercadillo e where nombre_servicio LIKE CONCAT('%', CONCAT(?1, '%'))")

	Page<Mercadillo> findPaginaByNombre_servicio(Pageable page,String nombre_servicio);
	
	@Query("select e from Mercadillo e where tipo_servicio=?1 and precio>=?2 ORDER BY precio")

	Page<Mercadillo> findPaginaByTipo_servicioPreciMin(Pageable page,String tipo_servicio,double precioMayorQue);
	
	@Query("select e from Mercadillo e where tipo_servicio=?1 and precio<=?2 ORDER BY precio")

	Page<Mercadillo> findPaginaByTipo_servicioPreciMax(Pageable page,String tipo_servicio,double precioMenorQue);
	
	@Query("select e from Mercadillo e where tipo_servicio=?1 and precio>=?2 and precio<=?3 ORDER BY precio")

	Page<Mercadillo> findPaginaByTipo_servicioPreciMaxMin(Pageable page,String tipo_servicio,double precioMayorQue,double precioMenorQue);
	
	@Query("select e from Mercadillo e where  precio>=?1 ORDER BY precio")

	Page<Mercadillo> findByPreciMin(Pageable page,double precioMayorQue);
	
	@Query("select e from Mercadillo e where  precio<=?1 ORDER BY precio")

	Page<Mercadillo> findByPreciMax(Pageable page,double precioMenorQue);
	
	@Query("select e from Mercadillo e where  precio>=?2 and precio<=?3 ORDER BY precio ")

	Page<Mercadillo> findByMaxMin(Pageable page,double precioMayorQue,double precioMenorQue);
	
	

}
