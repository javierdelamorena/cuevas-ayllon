package com.cuevasdeayllon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuevasdeayllon.entity.Propuestas;
import com.cuevasdeayllon.repository.PropuestaRepository;

@Service
public class PropuestaServiceImpl implements PropuestaService {

	@Autowired
	PropuestaRepository service;
	@Override
	public void save(Propuestas porpuesta) {
		service.save(porpuesta);
		
	}

	@Override
	public List<Propuestas> findAll() {// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public void deleteById(Propuestas propuesta) {
		
		service.deleteById(propuesta, 0);
		
	}

	@Override
	public Propuestas findByIdPropuesta(int idPropuesta) {
		
		return service.findByIdPropuesta(idPropuesta);
	}

	@Override
	public Propuestas findBtNombre(String nombre) {
		// TODO Auto-generated method stub
		return service.findBtNombre(nombre);
	}

}
