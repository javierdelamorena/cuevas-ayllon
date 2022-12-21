package com.cuevasdeayllon.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Anuncios;

public interface AnunciosRepository {
	
	List<Anuncios> listAnuncio();
	
	
	void insertarAnucio(Anuncios anuncio,MultipartFile foto) throws IOException;
	
	
	Anuncios recuperarAnuncio(int idAnuncio);
	
	
	void deleteAnuncio(int idAnuncio);
	
	
	void editarAnuncio(int idAnuncio,Anuncios anuncio);

}
