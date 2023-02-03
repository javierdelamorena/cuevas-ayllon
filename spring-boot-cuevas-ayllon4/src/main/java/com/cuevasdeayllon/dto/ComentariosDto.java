package com.cuevasdeayllon.dto;

import com.cuevasdeayllon.entity.Propuestas;
import com.cuevasdeayllon.entity.Usuario;

public class ComentariosDto {
	
	
	
	private int id;
	private String comentario;
	
	private String editable;
		
	
	private Propuestas propuesta;
	
	private Usuario usuario;

	public ComentariosDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public Propuestas getPropuesta() {
		return propuesta;
	}

	public void setPropuesta(Propuestas propuesta) {
		this.propuesta = propuesta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	


}
