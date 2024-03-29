package com.cuevasdeayllon.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="textoDerecha")
public class TextoDerecha implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtextoDerecha")
	private int idTextoDerecha;
	private String pagina;
	private String texto;
	public TextoDerecha() {
		
		
	}
	public int getIdTextoDerecha() {
		return idTextoDerecha;
	}
	public void setIdTextoDerecha(int idTextoDerecha) {
		this.idTextoDerecha = idTextoDerecha;
	}
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	

}
