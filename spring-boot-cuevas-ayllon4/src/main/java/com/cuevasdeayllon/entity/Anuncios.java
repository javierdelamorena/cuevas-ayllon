package com.cuevasdeayllon.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tablonanuncios")
public class Anuncios {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idanuncios")
	private int idAnuncios;
	
	private String titulo_anuncio;
	
	private String anuncio;
	
	private String foto_anuncio;
	
	private String video_anuncio;
	
	private String audio_anuncio;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	public Anuncios() {
		super();
	}

	public int getIdAnuncios() {
		return idAnuncios;
	}

	public void setIdAnuncios(int idAnuncios) {
		this.idAnuncios = idAnuncios;
	}

	public String getTitulo_anuncio() {
		return titulo_anuncio;
	}

	public void setTitulo_anuncio(String titulo_anuncio) {
		this.titulo_anuncio = titulo_anuncio;
	}

	public String getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(String anuncio) {
		this.anuncio = anuncio;
	}

	public String getFoto_anuncio() {
		return foto_anuncio;
	}

	public void setFoto_anuncio(String foto_anuncio) {
		this.foto_anuncio = foto_anuncio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getVideo_anuncio() {
		return video_anuncio;
	}

	public void setVideo_anuncio(String video_anuncio) {
		this.video_anuncio = video_anuncio;
	}

	public String getAudio_anuncio() {
		return audio_anuncio;
	}

	public void setAudio_anuncio(String audio_anuncio) {
		this.audio_anuncio = audio_anuncio;
	}
	
	

}
