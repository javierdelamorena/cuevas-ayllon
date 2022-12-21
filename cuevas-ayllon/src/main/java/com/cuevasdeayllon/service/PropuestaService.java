package com.cuevasdeayllon.service;

import java.util.List;

import com.cuevasdeayllon.entity.Propuestas;

public interface PropuestaService {
	
	void save(Propuestas porpuesta);
	
	List<Propuestas> findAll();
	
	void deleteById(int idPropuesta);
	
	Propuestas findByIdPropuesta(int idPropuesta);
	
	Propuestas findBtNombre(String nombre);
	
	List<Propuestas> findById_Usuario(int id_usuario);
	
	Propuestas  editarPropuesta(Propuestas propuestas);
		

}
