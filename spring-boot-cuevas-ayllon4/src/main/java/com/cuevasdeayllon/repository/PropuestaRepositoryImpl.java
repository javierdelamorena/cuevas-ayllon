package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cuevasdeayllon.entity.Propuestas;
@Repository
public class PropuestaRepositoryImpl implements PropuestaRepository {

	@Autowired
	private PropuestaJpaRepository repository;
	@Override
	public void save(Propuestas prorpuesta) {
		repository.save(prorpuesta);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Propuestas> findAll() {
		
		return repository.findAll();
	}

	
	@Override
	@Transactional
	public Propuestas findByIdPropuesta(int idPropuesta) {
		// TODO Auto-generated method stub
		return repository.findById(idPropuesta);
	}

	@Override
	public Propuestas findBtNombre(String titulo) {
		// TODO Auto-generated method stub
		return repository.findByTitulo(titulo);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<Propuestas> findById_Usuario(int id_usuario) {
		
		return repository.findById_Uusuario(id_usuario);
	}
	

	
	
}
