package com.cuevasdeayllon.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="propuestas")
public class Propuestas implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_propuesta")
	int idPropuesta;
	
	private String propuesta;
	private String titulo;
	

	@ManyToOne
	@JoinColumn(name="id_usuario",referencedColumnName ="id_usuario" )
	@JsonBackReference
	private Usuario usuario;
	@OneToMany(mappedBy="propuesta",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Comentarios> comentario;
	

	public Propuestas() {
		super();
	}

	
	
	


	public String getPropuesta() {
		return propuesta;
	}

	public void setPropuesta(String propuesta) {
		this.propuesta = propuesta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public List<Comentarios> getComentario() {
		return comentario;
	}


	public void setComentario(List<Comentarios> comentario) {
		this.comentario = comentario;
	}


	public int getIdPropuesta() {
		return idPropuesta;
	}


	public void setIdPropuesta(int idPropuesta) {
		this.idPropuesta = idPropuesta;
	}


	

	


}
