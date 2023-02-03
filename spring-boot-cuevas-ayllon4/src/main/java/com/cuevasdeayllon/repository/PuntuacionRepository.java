package com.cuevasdeayllon.repository;

import java.util.List;

import com.cuevasdeayllon.entity.Puntuacion;

public interface PuntuacionRepository {

	void grabarPuntuacion(Puntuacion puntuacion);
	
	Puntuacion puntuacionDePropuesta(String idUsuario,String propuesta);
	
	List<Puntuacion> listaDePuntos(String propuesta);
	
	
	
}
