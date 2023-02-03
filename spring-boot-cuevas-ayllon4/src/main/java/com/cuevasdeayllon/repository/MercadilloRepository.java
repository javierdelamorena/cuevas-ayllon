package com.cuevasdeayllon.repository;

import java.util.List;

import com.cuevasdeayllon.entity.Mercadillo;

public interface MercadilloRepository {
	
	
	List<Mercadillo> todosLosMercadillos();
	
	List<Mercadillo> todosLosMercadillosiIdUsuario(int idUsuario);
	
	Mercadillo findById(int id);
	
	
	Mercadillo findByTipoServicio(String tipoServicio);
	
	
	
	void insertarMercadillo(Mercadillo mercadillo);
	
	void borrarMercadillo(int id);
	
	void actualizarMercadillo(Mercadillo mercadillo);

}
