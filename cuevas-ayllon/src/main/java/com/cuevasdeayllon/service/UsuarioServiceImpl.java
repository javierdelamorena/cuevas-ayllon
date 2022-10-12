package com.cuevasdeayllon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuevasdeayllon.entity.Usuario;
import com.cuevasdeayllon.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    
	@Autowired
	UsuarioRepository usuarioService;
	
	@Override
	public Usuario usuarioPorId(int idUsuario) {
		
		
		return usuarioService.usuarioPorId(idUsuario);
	}

	@Override
	public void salvarUsuario(Usuario usuario) {
		Usuario usuarios=new Usuario();
		
		usuarios.setIdUsuario(0);
		usuarios.setNombre(usuario.getNombre());
		usuarios.setApellido1(usuario.getApellido1());
		usuarios.setApellido2(usuario.getApellido2());
		usuarios.setEmail(usuario.getEmail());		
		usuarios.setPassword(usuario.getPassword());
		usuarios.setFoto(usuario.getFoto());
		usuarios.setEnabled(true);
		usuarios.setDireccion(usuario.getDireccion());
		usuarios.setRoles("USER");
		 
		usuarioService.salvarUsuario(usuarios);
		
	}

	@Override
	public List<Usuario> todosLosUsuarios() {
		// TODO Auto-generated method stub
		return usuarioService.todosLosUsuarios();
	}

	@Override
	public Usuario usuarioPorNombre(String nombre) {
	Usuario usuario=usuarioService.usuarioPorNombre(nombre);
	
	if(usuario!=null) {
		
		return usuarioService.usuarioPorNombre(nombre);
		
	}
	return null;
		
	}

}
