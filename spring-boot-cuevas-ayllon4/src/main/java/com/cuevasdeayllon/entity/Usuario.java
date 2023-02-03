package com.cuevasdeayllon.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;
	@NotEmpty
	
	private String nombre;
	@NotEmpty
	private String apellido1;
	@NotEmpty
	private String apellido2;
	@Email
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	private String notificacion;
	private String foto;
	private String roles;
	@NotEmpty
	private String direccion;
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Propuestas> propuestas;
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Comentarios> comentarios;
	@PrePersist
	
	public void prePersist() {
		createAt=new Date();
	}
	public Usuario() {
		super();
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	private Boolean enabled;
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public List<Propuestas> getPropuestas() {
		return propuestas;
	}
	public void setPropuestas(List<Propuestas> propuestas) {
		this.propuestas = propuestas;
	}
	
	public List<Comentarios> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentarios> comentarios) {
		this.comentarios = comentarios;
	}
	
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getNotificacion() {
		return notificacion;
	}
	public void setNotificacion(String notificacion) {
		this.notificacion = notificacion;
	}
	private static final long serialVersionUID = 1L;
}
