package com.cuevasdeayllon.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Anuncios;

@Repository
public class AnuncioRepositoryImpl implements AnunciosRepository {

	@Autowired
	private AnuncioJpaRepository anuncioJpaRepository;

	@Override
	public void insertarAnucio(Anuncios anuncio)  {
		

			anuncioJpaRepository.save(anuncio);

		


		


	}

	@Override
	public Anuncios recuperarAnuncio(int idAnuncio) {

		return anuncioJpaRepository.findById(idAnuncio).orElse(null);
	}

	@Override
	public void deleteAnuncio(int idAnuncio) {

		anuncioJpaRepository.deleteById(idAnuncio);

	}

	@Override
	public void editarAnuncio(Anuncios anuncio) {
		
		

		anuncioJpaRepository.save(anuncio);

	}

	@Override
	public List<Anuncios> listAnuncio() {

		return anuncioJpaRepository.findAll();
	}




}
