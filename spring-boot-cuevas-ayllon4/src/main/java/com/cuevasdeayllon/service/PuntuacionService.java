package com.cuevasdeayllon.service;

import java.util.List;

import com.cuevasdeayllon.entity.Puntuacion;

public interface PuntuacionService {
	
	void grabarPuntuacion(Puntuacion puntuacion);
	
	Puntuacion puntuacionDePropuesta(String idUsuario,String propuesta);
	
	 List<Puntuacion> listaDePuntos(String propuesta);
	
	
	

}
