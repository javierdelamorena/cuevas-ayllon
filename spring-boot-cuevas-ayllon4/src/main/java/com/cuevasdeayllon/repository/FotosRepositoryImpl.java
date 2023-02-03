package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.cuevasdeayllon.entity.Fotos;

@Repository
public class FotosRepositoryImpl implements FotosRepository{

	@Autowired
	FotosJpaRepository repository;


	public List<Fotos> todasLasFotos(){

		return repository.findAll();

	}


	@Override
	public void salvarFoto(Fotos foto) {

		repository.save(foto);

	}


	@Override
	public void deleteFoto(Fotos foto) {
		
		repository.delete(foto);
	}


	


	@Override
	public Page<Fotos> fotosPaginas(Pageable page) {
		
		return repository.findAllPage(page);
	};

}
