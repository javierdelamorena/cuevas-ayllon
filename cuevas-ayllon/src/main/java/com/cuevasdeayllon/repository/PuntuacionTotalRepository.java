package com.cuevasdeayllon.repository;

import java.util.List;

import com.cuevasdeayllon.entity.PuntuacionTotal;

public interface PuntuacionTotalRepository {
	
	
	void salvarPuntuacion(String propuesta,int puntuacion);
	
	List<PuntuacionTotal> todasLasPuntuaciones();
	
	void borrarPuntuacion(int idPuntuacion);

}
