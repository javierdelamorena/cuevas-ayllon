package com.cuevasdeayllon.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Mercadillo;

public interface MercadilloService {

	List<Mercadillo> todosLosMercadillos();

	Page<Mercadillo> todasPaginasMercadillo(Pageable page);

	List<Mercadillo> todosLosMercadillosiIdUsuario(int idUsuario);

	List<Mercadillo> findByTipo_servicio(String tipo_servicio);

	Page<Mercadillo> findPaginaByNombre_servicio(Pageable page, String nombre_servicio);

	Page<Mercadillo> findPaginaByTipo_servicio(Pageable page, String tipo_servicio);
	
	

	Page<Mercadillo> findByMax(Pageable page, int precioMax);

	Page<Mercadillo> findByMin(Pageable page, int precioMin);

	Page<Mercadillo> findByMaxMin(Pageable page, int precioMin, int precioMax);
	
	

	Page<Mercadillo> findPaginasByTipoServicioPrecioMax(Pageable page, String tipo_servicio, int precioMax);

	Page<Mercadillo> findPaginasByTipoServicioPrecioMin(Pageable page, String tipo_servicio, int precioMin);

	Page<Mercadillo> findPaginasByTipoServicioPrecioMaxMin(Pageable page, String tipo_servicio, int precioMin,
			int precioMax);

	Mercadillo findById(int id);

	Mercadillo findByTipoServicio(String tipoServicio);

	void insertarMercadillo(Mercadillo mercadillo, MultipartFile foto1, MultipartFile foto2, MultipartFile foto3);

	void borrarMercadillo(int id);

	void actualizarMercadillo(Mercadillo mercadillo, MultipartFile foto1, MultipartFile foto2, MultipartFile foto3);

}
