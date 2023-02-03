package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cuevasdeayllon.entity.Fotos;

public interface FotosRepository {
	
	public List<Fotos> todasLasFotos();
	
	void salvarFoto(Fotos foto);
	
	void deleteFoto(Fotos foto);
	
	Page<Fotos> fotosPaginas(Pageable page);

}
