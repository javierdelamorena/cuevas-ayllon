package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuevasdeayllon.entity.Recuperar;

@Repository
public class RecuperarRepositoryImpl implements RecuperarRepository {
	@Autowired 
	RecuperarJpaRepository repository;

	@Override
	public void insertar(Recuperar recuperar) {
		
		try {
		repository.save(recuperar);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void borrar(int id) {
		repository.deleteById(id);
		
	}

	@Override
	public Recuperar recuperarPorMail(String mail) {
		
		return repository.findByEmail(mail);
	}

	@Override
	public Recuperar recuperarPorIdUsuario(int idUsuario) {
		
		return repository.findById(idUsuario).orElse(null);
	}

	@Override
	public Recuperar recuperarPorTken(String token) {
		// TODO Auto-generated method stub
		return repository.findByToken(token);
	}

	@Override
	public void refrecar() {
		repository.flush();
		
	}

	@Override
	public List<Recuperar> todoeLosRecurar() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void borrarTodosRecuperar() {
		repository.deleteAll();
		
	}

}
