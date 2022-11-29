package com.cuevasdeayllon.dto;

import java.util.List;

import com.cuevasdeayllon.entity.Comentarios;
import com.cuevasdeayllon.entity.Propuestas;
import com.cuevasdeayllon.entity.Puntuacion;
import com.cuevasdeayllon.entity.Usuario;

public class Objetos {
	
	private Usuario usuario;
	private Propuestas propuestas;
	
	private List<Comentarios> comentarios;
	private Puntuacion puntuacion;
	
	public Objetos(List<Comentarios> comentarios) {
		super();
		this.comentarios = comentarios;
	}
	public Objetos() {
		
		super();
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Propuestas getPropuestas() {
		return propuestas;
	}
	public void setPropuestas(Propuestas propuestas) {
		this.propuestas = propuestas;
	}
	
	public Puntuacion getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Puntuacion puntuacion) {
		this.puntuacion = puntuacion;
	}
	public List<Comentarios> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentarios> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
	
	

}
