package com.cuevasdeayllon.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Anuncios;
import com.cuevasdeayllon.repository.AnuncioRepositoryImpl;

@CrossOrigin(origins = "*")
@Controller
public class AnunciosController {

	private static final Logger logger = LoggerFactory.getLogger(FotosController.class);

	@Autowired
	private AnuncioRepositoryImpl anuncioRepositoryImpl;

	@GetMapping("/todosAnuncios")
	public String listaDeAnuncios( Model model) {


		List <Anuncios>anuncios=anuncioRepositoryImpl.listAnuncio();

		model.addAttribute("listaAnuncios",anuncios);

		return "tablonanuncios";

	}
	@PostMapping("/subirAnuncio")
	public String insertarAnuncio( @RequestParam("titulo")String titulo,@RequestParam("anuncio")String anuncio,@RequestParam("file")MultipartFile foto, Model model) {


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





	}
	@GetMapping("/listaAnunciosAdmin")
	public String listaDeAnunciosAdministrador( Model model) {


		List <Anuncios>anuncios=anuncioRepositoryImpl.listAnuncio();

		model.addAttribute("listaAnuncios",anuncios);

		return "listaAnuncios";

	}
	@PostMapping("/borrarAnuncio")
	public String borrarAnuncio(@RequestParam("idAnuncio")int idAnuncio, Model model) {

		if(idAnuncio>0) {
			anuncioRepositoryImpl.deleteAnuncio(idAnuncio);
		}
		List <Anuncios>anuncios=anuncioRepositoryImpl.listAnuncio();
		model.addAttribute("listaAnuncios",anuncios);

		return "listaAnuncios";

	}


}
