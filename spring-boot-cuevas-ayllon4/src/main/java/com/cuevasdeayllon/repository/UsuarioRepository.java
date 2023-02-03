package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cuevasdeayllon.entity.Usuario;


public interface UsuarioRepository {
	
	Usuario usuarioPorId(int idUsuario);
	void salvarUsuario(Usuario usuario);
	List<Usuario> todosLosUsuarios();
	
	Usuario usuarioPorNombre(String nombre);
	void borrarUsuarioi(Usuario usuario);
	
	Usuario usuarioPormail(String email);
}
