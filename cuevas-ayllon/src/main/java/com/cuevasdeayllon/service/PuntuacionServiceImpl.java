package com.cuevasdeayllon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuevasdeayllon.entity.Puntuacion;
import com.cuevasdeayllon.repository.PuntuacionRepository;

@Service
public class PuntuacionServiceImpl implements PuntuacionService{
	
	@Autowired
	PuntuacionRepository puntuacionRepository;

	@Override
	public void grabarPuntuacion(Puntuacion puntuacion) {
		
		
		puntuacionRepository.grabarPuntuacion(puntuacion);
		
	}

	@Override
	public Puntuacion puntuacionDePropuesta(String idUsuario, String propuesta) {
		
		return  puntuacionRepository.puntuacionDePropuesta(idUsuario, propuesta);
	}

	@Override
	public List<Puntuacion> listaDePuntos(String propuesta) {
		
		return puntuacionRepository.listaDePuntos(propuesta);
	}

}
