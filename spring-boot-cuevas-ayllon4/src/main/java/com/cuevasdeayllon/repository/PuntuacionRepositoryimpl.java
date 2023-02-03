package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuevasdeayllon.entity.Puntuacion;

@Repository
public class PuntuacionRepositoryimpl implements PuntuacionRepository{
	
	@Autowired
	PuntuacionJpaRepository puntuacionJpaRepository;

	@Override
	public void grabarPuntuacion(Puntuacion puntuacion) {


		puntuacionJpaRepository.saveAndFlush(puntuacion);
		
	}

	@Override
	public Puntuacion puntuacionDePropuesta(String idUsuario, String propuesta) {
		
		return puntuacionJpaRepository.puntuacionDePropuesta(idUsuario, propuesta);
	}

	@Override
	public List<Puntuacion> listaDePuntos(String propuesta) {
		
		return puntuacionJpaRepository.listaDePuntos(propuesta);
		
	}

	
}
