package com.cuevasdeayllon.repository;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cuevasdeayllon.entity.Comentarios;

@Repository
public class ComentarioRepositoryImpl implements ComentarioRepository{

	@Autowired
	ComentarioJpaRepository repository;
	@Override
	@Transactional
	public void save(Comentarios comentario) {
		repository.save(comentario);
		
	}

	

	@Override
	@Transactional
	public void deleteById(Comentarios comentario, int id) {
		
		repository.delete(comentario);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Comentarios> findAllByIdPropuesta(int idPropuesta) {
		
		return repository.findByIdPropuesta(idPropuesta);
	}



	@Override
	public Comentarios findByComentario(String comentario) {
		
		return repository.findByComentario(comentario);
	}



	@Override
	public void deleteByComentario(String comentario) {
		repository.deleteByComentario(comentario);
		
	}



	@Override
	public List<Comentarios> findAllComentarios() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}



	@Override
	public Comentarios findByid(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}

}
