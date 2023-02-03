package com.cuevasdeayllon.repository;

import java.util.List;

import com.cuevasdeayllon.entity.Recuperar;

public interface RecuperarRepository {
	
	
	void insertar(Recuperar recuperar);
	
	void borrar(int id);
	
	Recuperar recuperarPorMail(String mail);
	
	Recuperar recuperarPorIdUsuario(int idUsuario);
	
	Recuperar recuperarPorTken(String token);
	
	void refrecar();
	
	List<Recuperar> todoeLosRecurar();
	
	void borrarTodosRecuperar();

}
