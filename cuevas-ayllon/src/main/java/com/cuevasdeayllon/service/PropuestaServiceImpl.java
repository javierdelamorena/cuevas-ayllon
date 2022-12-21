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
	public void deleteById(int id) {
		
		service.deleteById(id);
		
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

	@Override
	public List<Propuestas> findById_Usuario(int id_usuario) {
		
		return service.findById_Usuario(id_usuario);
	}

	@Override
	public Propuestas editarPropuesta(Propuestas propuestas) {
		
		Propuestas miPropuesta=new Propuestas();
		
		miPropuesta.setIdPropuesta(propuestas.getIdPropuesta());
		miPropuesta.setPropuesta(propuestas.getPropuesta());
		miPropuesta.setTitulo(propuestas.getTitulo());
		miPropuesta.setComentario(propuestas.getComentario());
		miPropuesta.setUsuario(propuestas.getUsuario());
		service.save(miPropuesta);
		
		return null;
	}

}
