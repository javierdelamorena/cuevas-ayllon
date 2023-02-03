package com.cuevasdeayllon.repository;

import java.util.List;

import com.cuevasdeayllon.entity.Comentarios;

public interface ComentarioRepository {
	
	void save(Comentarios porpuesta);
	
	List<Comentarios> findAllByIdPropuesta(int idPropuesta);
	
	void deleteById(Comentarios propuesta,int id);
	
	Comentarios findByComentario(String comentario);
	
	void deleteByComentario(String comentario);
	
	List<Comentarios> findAllComentarios();
	
	Comentarios findByid(int id);

}
