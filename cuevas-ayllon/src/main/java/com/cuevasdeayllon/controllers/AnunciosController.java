package com.cuevasdeayllon.controllers;


import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.cuevasdeayllon.entity.Anuncios;
import com.cuevasdeayllon.repository.AnuncioRepositoryImpl;

@CrossOrigin(origins = "*")
@Controller
public class AnunciosController {

	private static final Logger logger = LoggerFactory.getLogger(AnunciosController.class);

	@Autowired
	private AnuncioRepositoryImpl anuncioRepositoryImpl;

	@GetMapping("/todosAnuncios")
	public String listaDeAnuncios( Model model) {

		logger.info("Entramos en metodo /todosAnuncios");

		try {
			List <Anuncios>anuncios= anuncioRepositoryImpl.listAnuncio();

			anuncios=anuncios.stream().sorted(Comparator.comparing(Anuncios::getIdAnuncios)
					.reversed()).collect(Collectors.toList());

			model.addAttribute("listaAnuncios",anuncios);

			return "tablonanuncios";

		}catch(Exception e) {

			logger.info("El error que da en /todosAnuncios es: "+e.getMessage());

		}
		return "tablonanuncios";

	}
	@PostMapping("/subirAnuncio")
	public String insertarAnuncio( @RequestParam("titulo")String titulo,@RequestParam("anuncio")String anuncio,@RequestParam("file")MultipartFile foto, Model model) {


		logger.info("Entramos en metodo /subirAnuncio");

		int oraLen = foto.getOriginalFilename().length();
		logger.info("El nombre de la foto es: "+foto.getOriginalFilename());

		for (int i = 0; i <  oraLen; i++) {
			if (foto.getOriginalFilename().charAt(i) == ' ') {
				model.addAttribute("faltaTitulo", "El nombre de la foto no puede tener espacios en blanco.");
				return "subirAnuncio";

			}
		}


		try {
			if(titulo.isEmpty()) {

				model.addAttribute("faltaTitulo", "El titulo en los anuncios es necesario, lo unico que se puede omitir es la foto.");
				return "subirAnuncio";
			}

			else if(anuncio.isEmpty()) {

				model.addAttribute("faltaAnuncio", "El texto en los anuncios es necesario, lo unico que se puede omitir es la foto.");
				return "subirAnuncio";
			}else {
				Anuncios anuncioeditable=new Anuncios();
				anuncioeditable.setIdAnuncios(0);
				anuncioeditable.setAnuncio(anuncio);
				anuncioeditable.setFecha(new Date());		
				anuncioeditable.setTitulo_anuncio(titulo);

				anuncioRepositoryImpl.insertarAnucio(anuncioeditable,foto);

				model.addAttribute("anuncioSubido", "El anuncio se ha agregado con exito.");

				return "subirAnuncio";
			}
		}catch(Exception e) {

			model.addAttribute("anuncioSubido", "El anuncio no se ha agregado.");

			logger.info("El error que da en /subirAnuncio es: "+e.getMessage());
		}
		return "subirAnuncio";

	}
	@GetMapping("/listaAnunciosAdmin")
	public String listaDeAnunciosAdministrador( Model model) {

		logger.info("Entramos en metodo /listaAnunciosAdmin");
		try {
			List <Anuncios>anuncios=anuncioRepositoryImpl.listAnuncio();


			model.addAttribute("listaAnuncios",anuncios);


			return "listaAnuncios";
		}catch(Exception e) {
			logger.info("El error que da en /listaAnunciosAdmin es: "+e.getMessage());
		}
		return "listaAnuncios";

	}
	@PostMapping("/borrarAnuncio")
	public String borrarAnuncio(@RequestParam("idAnuncio")int idAnuncio, Model model) {
		try {
			if(idAnuncio>0) {
				anuncioRepositoryImpl.deleteAnuncio(idAnuncio);
			}

		}catch(Exception e) {

			logger.info("El error que da en /borrarAnuncio es: "+e.getMessage());

		}

		List <Anuncios>anuncios;
		try {
			anuncios=anuncioRepositoryImpl.listAnuncio();
			model.addAttribute("listaAnuncios",anuncios);
		}catch(Exception e) {

			logger.info("El error que da en /borrarAnuncio recuperar todos es: "+e.getMessage());

		}

		return "listaAnuncios";

	}


}
