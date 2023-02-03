package com.cuevasdeayllon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuevasdeayllon.entity.Comentarios;
import com.cuevasdeayllon.entity.Propuestas;
import com.cuevasdeayllon.repository.ComentarioRepository;


@Service
public class ComentarioServiceImpl implements ComentarioService{
	@Autowired
	ComentarioRepository service;

	@Override
	@Transactional
	public void save(Comentarios comentario) {
		service.save(comentario);
		
	}

	@Override
	public List<Comentarios> findAllByIdPropuesta(int idPropuesta) {
		// TODO Auto-generated method stub
		return service.findAllByIdPropuesta(idPropuesta);
	}

	@Override
	@Transactional
	public void deleteById(Comentarios propuesta, int id) {
		service.deleteById(propuesta, id);
		
	}

	@Override
	@Transactional
	public Comentarios findByComentario(String comentario) {
		
		return service.findByComentario(comentario);
	}

	@Override
	@Transactional
	public void deleteByComentario(String comentario) {

    service.deleteByComentario(comentario); 
		
	}

	@Override
	public List<Comentarios> findAll() {
		// TODO Auto-generated method stub
		return service.findAllComentarios();
	}

	@Override
	public Comentarios findByid(int id) {
		
		return service.findByid(id);
	}

	@Override
	public void editarComentaio(Comentarios comentario) {
		Comentarios coment=new Comentarios();
		coment.setComentario(comentario.getComentario());
		coment.setId(comentario.getId());
		coment.setPropuesta(comentario.getPropuesta());
		coment.setUsuario(comentario.getUsuario());
		service.save(coment);
		
	}

}
