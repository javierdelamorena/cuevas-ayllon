package com.cuevasdeayllon.service;

import java.util.List;

import com.cuevasdeayllon.entity.Comentarios;
import com.cuevasdeayllon.entity.Propuestas;

public interface ComentarioService {
	
	
     void save(Comentarios porpuesta);
	
	List<Comentarios> findAllByIdPropuesta(int idPropuesta);
	
	void deleteById(Comentarios propuesta,int id);
	
	Comentarios findByComentario(String comentario);
	
	void deleteByComentario(String comentario);
	
	
	List<Comentarios> findAll();
	
	Comentarios findByid(int id);
	
	void editarComentaio(Comentarios comentario);
	

}
