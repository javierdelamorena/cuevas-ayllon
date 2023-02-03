package com.cuevasdeayllon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fotos")
public class Fotos {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idfotos")
	private int idFotos;
	
	private String fotos;
	
	int id_usuario;

	public Fotos() {
		super();
	}

	public int getIdFotos() {
		return idFotos;
	}

	public void setIdFotos(int idFotos) {
		this.idFotos = idFotos;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	
	
	
	
	

}
