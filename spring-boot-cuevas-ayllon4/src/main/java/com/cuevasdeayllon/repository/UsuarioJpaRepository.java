package com.cuevasdeayllon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuevasdeayllon.entity.Usuario;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Integer>{
	
	
	Usuario findByNombre(String nombre);
	
	
	Usuario findByEmail(String email);
	
	

}
