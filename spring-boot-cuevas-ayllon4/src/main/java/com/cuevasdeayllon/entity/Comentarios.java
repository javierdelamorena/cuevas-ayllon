package com.cuevasdeayllon.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="comentarios")
public class Comentarios implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	private String comentario;
	
	private String editable;
		
	@ManyToOne
	@JoinColumn(name="id_propuesta",referencedColumnName ="id_propuesta")
	@JsonBackReference
	private Propuestas propuesta;
	@ManyToOne
	@JoinColumn(name="id_usuario",referencedColumnName ="id_usuario" )
	@JsonBackReference
	private Usuario usuario;

	public Comentarios() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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
