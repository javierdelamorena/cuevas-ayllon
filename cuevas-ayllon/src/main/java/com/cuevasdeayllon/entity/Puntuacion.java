package com.cuevasdeayllon.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="puntuacion")
public class Puntuacion implements Serializable{
	

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_puntuacion;
	
	private String usuario;
	
	private int puntuacion;
	
	private String propuesta;

	
	public int getId_puntuacion() {
		return id_puntuacion;
	}

	public void setId_puntuacion(int id_puntuacion) {
		this.id_puntuacion = id_puntuacion;
	}

	
	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getPropuesta() {
		return propuesta;
	}

	public void setPropuesta(String propuesta) {
		this.propuesta = propuesta;
	}
	
	
	

}
