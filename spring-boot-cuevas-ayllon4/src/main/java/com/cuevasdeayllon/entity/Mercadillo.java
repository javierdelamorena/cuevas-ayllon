package com.cuevasdeayllon.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="mercadillo")
public class Mercadillo implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private int id_usuario;	
	@Min(1)	
	
	private Double precio;	
	private String foto1;
	private String foto2;
	private String foto3;
	@NotEmpty
	private String texto;
	@NotEmpty
	private String telefono;
	
	@NotEmpty
	private String tipo_servicio;
	
	private Date fecha;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String nombre_servicio;
	
	public Mercadillo() {
		super();
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getId_usuario() {
		return id_usuario;
	}




	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}




	public Double getPrecio() {
		return precio;
	}




	public void setPrecio(Double precio) {
		this.precio = precio;
	}




	public String getFoto1() {
		return foto1;
	}




	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}




	public String getFoto2() {
		return foto2;
	}




	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}




	public String getFoto3() {
		return foto3;
	}




	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}




	public String getTexto() {
		return texto;
	}




	public void setTexto(String texto) {
		this.texto = texto;
	}




	




	public String getTelefono() {
		return telefono;
	}




	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




	public String getTipo_servicio() {
		return tipo_servicio;
	}




	public void setTipo_servicio(String tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}




	public Date getFecha() {
		return fecha;
	}




	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getNombre_servicio() {
		return nombre_servicio;
	}




	public void setNombre_servicio(String nombre_servicio) {
		this.nombre_servicio = nombre_servicio;
	}




	private static final long serialVersionUID = 1L;

}
