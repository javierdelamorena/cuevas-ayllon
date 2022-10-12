package com.cuevasdeayllon.service;

import java.util.List;

import com.cuevasdeayllon.entity.Propuestas;

public interface PropuestaService {
	
	void save(Propuestas porpuesta);
	List<Propuestas> findAll();
	
	void deleteById(Propuestas propuesta);
	
	Propuestas findByIdPropuesta(int idPropuesta);
	
	Propuestas findBtNombre(String nombre);
		

}
