package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cuevasdeayllon.entity.Usuario;

@Repository
public class UsuarioiRepositoryImpl implements UsuarioRepository {

	@Autowired
	UsuarioJpaRepository usuarioRepository;
	@Override
	@Transactional(readOnly=true)
	public Usuario usuarioPorId(int idUsuario) {
		
		return usuarioRepository.findById(idUsuario).orElse(null);
	}

	@Override
	
	public void salvarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> todosLosUsuarios() {
		
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario usuarioPorNombre(String nombre) {
		
		return usuarioRepository.findByNombre(nombre);
	}

	@Override
	public void borrarUsuarioi(Usuario usuario) {
		usuarioRepository.delete(usuario);
		
		
	}

	@Override
	public Usuario usuarioPormail(String email) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByEmail(email);
	}

}
