package com.cuevasdeayllon.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Anuncios;

@Repository
public class AnuncioRepositoryImpl implements AnunciosRepository {

	@Autowired
	private AnuncioJpaRepository anuncioJpaRepository;

	@Override
	public void insertarAnucio(Anuncios anuncio,MultipartFile foto)  {
		//String rootPath="C://TEMP//uploadsAnuncios";
		String rootPath="/uploadsAnuncios/";
		Anuncios anuncioeditable=new Anuncios();
		anuncioeditable.setIdAnuncios(0);
		anuncioeditable.setAnuncio(anuncio.getAnuncio());
		anuncioeditable.setFecha(new Date());		
		anuncioeditable.setTitulo_anuncio(anuncio.getTitulo_anuncio());

		if(!foto.isEmpty()) {


			try {
				byte[]bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+foto.getOriginalFilename());
				Files.write(rutaCompleta,bytes);
				
				anuncioeditable.setFoto_anuncio(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			anuncioJpaRepository.save(anuncioeditable);

		}
		else {

			anuncioeditable.setFoto_anuncio(null);
			anuncioJpaRepository.save(anuncioeditable);



		}


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
	public void editarAnuncio(int idAnuncio ,Anuncios anuncio) {
		
		Anuncios anuncioeditable=new Anuncios();
		anuncioeditable.setAnuncio(anuncio.getAnuncio());
		anuncioeditable.setFecha(anuncio.getFecha());
		anuncioeditable.setIdAnuncios(anuncio.getIdAnuncios());
		anuncioeditable.setTitulo_anuncio(anuncio.getTitulo_anuncio());
		anuncioeditable.setFoto_anuncio(anuncio.getFoto_anuncio());

		anuncioJpaRepository.save(anuncioeditable);

	}

	@Override
	public List<Anuncios> listAnuncio() {

		return anuncioJpaRepository.findAll();
	}




}
