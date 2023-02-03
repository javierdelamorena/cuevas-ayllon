package com.cuevasdeayllon.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cuevasdeayllon.entity.Usuario;
import com.cuevasdeayllon.repository.UsuarioRepository;
@Service("UsuarioDetailsService")
public class UsuarioDetailsService implements UserDetailsService{
	private static final Logger logger=LoggerFactory.getLogger(UsuarioDetailsService.class);
	@Autowired
	private UsuarioRepository service;
	
	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
		if(nombre ==null) {
			logger.info("el nombre esta vacio en loadUserByUsername");
		}
		Usuario usuario=service.usuarioPorNombre(nombre);
		
		logger.info("el nombre en user detais es:"+nombre);
		List<GrantedAuthority>authorities=new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(usuario.getRoles().toString()));
		
		UserDetails userDetails	=new User(nombre,usuario.getPassword(),authorities);
		
		
		return userDetails;
	}

	

}
