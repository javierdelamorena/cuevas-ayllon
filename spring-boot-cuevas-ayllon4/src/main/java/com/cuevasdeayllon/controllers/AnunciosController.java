package com.cuevasdeayllon.controllers;

import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Anuncios;
import com.cuevasdeayllon.entity.Documentos;
import com.cuevasdeayllon.repository.AnuncioRepositoryImpl;
import com.cuevasdeayllon.repository.DocumentosRepositoryImpl;

@CrossOrigin(origins = "*")
@Controller
public class AnunciosController {

	private static final Logger logger = LoggerFactory.getLogger(AnunciosController.class);

	@Autowired
	private AnuncioRepositoryImpl anuncioRepositoryImpl;

	@Autowired
	private DocumentosRepositoryImpl documentosRepositoryImpl;

	@GetMapping("/todosAnuncios")
	public String listaDeAnuncios(Model model) {

		logger.info("Entramos en metodo /todosAnuncios");

		try {
			List<Anuncios> anuncios = anuncioRepositoryImpl.listAnuncio();

			anuncios = anuncios.stream().sorted(Comparator.comparing(Anuncios::getIdAnuncios).reversed())
					.collect(Collectors.toList());

			model.addAttribute("listaAnuncios", anuncios);

			return "tablonanuncios";

		} catch (Exception e) {

			logger.info("El error que da en /todosAnuncios es: " + e.getMessage());

		}
		return "tablonanuncios";

	}

	@PostMapping("/subirAnuncio")
	public String insertarAnuncio(@RequestParam("titulo") String titulo, @RequestParam("anuncio") String anuncio,
			@RequestParam("file") MultipartFile file, Model model) {

		 String rootPath="/uploadsAnuncios/";
		//String rootPath = "C://TEMP//uploadsAnuncios";
		logger.info("Entramos en metodo /subirAnuncio");

		int oraLen = file.getOriginalFilename().length();
		logger.info("El nombre de la foto es: " + file.getOriginalFilename());

		for (int i = 0; i < oraLen; i++) {
			if (file.getOriginalFilename().charAt(i) == ' ') {
				model.addAttribute("fotoConEspacio", "El nombre de la foto no puede tener espacios en blanco.");
				return "subirAnuncio";

			}
		}

		try {
			if (titulo.isEmpty()) {

				model.addAttribute("faltaTitulo",
						"El titulo en los anuncios es necesario, lo unico que se puede omitir es la foto.");
				return "subirAnuncio";
			}

			else if (anuncio.isEmpty()) {

				model.addAttribute("faltaAnuncio",
						"El texto en los anuncios es necesario, lo unico que se puede omitir es la foto.");
				return "subirAnuncio";
			} else {
				Anuncios anuncioeditable = new Anuncios();
				anuncioeditable.setIdAnuncios(0);
				anuncioeditable.setAnuncio(anuncio);
				anuncioeditable.setFecha(new Date());
				anuncioeditable.setTitulo_anuncio(titulo);
				if (!file.isEmpty()) {

					try {
						byte[] bytes = file.getBytes();
						Path rutaCompleta = Paths.get(rootPath + "//" + file.getOriginalFilename());
						Files.write(rutaCompleta, bytes);
						String extension = FilenameUtils.getExtension(file.getOriginalFilename());
						if(extension.equals("mp4")) {
							anuncioeditable.setVideo_anuncio(file.getOriginalFilename());
							
						}else if(extension.equals("jpg")||extension.equals("png")) {
							anuncioeditable.setFoto_anuncio(file.getOriginalFilename());
						}
						else {
							anuncioeditable.setAudio_anuncio(file.getOriginalFilename());
							
						}

						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				anuncioRepositoryImpl.insertarAnucio(anuncioeditable);

				model.addAttribute("anuncioSubido", "El anuncio se ha agregado con exito.");

				return "subirAnuncio";
			}
		} catch (Exception e) {

			model.addAttribute("anuncioSubido", "El anuncio no se ha agregado.");

			logger.info("El error que da en /subirAnuncio es: " + e.getMessage());
		}
		return "subirAnuncio";

	}

	@PostMapping("/editarAnuncio")
	public String editarAnuncio(@RequestParam("idAnuncio") int idAnuncio, @RequestParam("titulo") String titulo,
			@RequestParam("fecha") String fecha, @RequestParam("anuncio") String anuncio,
			@RequestParam("file") MultipartFile file, Model model) {

		 String rootPath="/uploadsAnuncios/";
		//String rootPath = "C://TEMP//uploadsAnuncios";
		logger.info("Entramos en metodo /subirAnuncio");
		Anuncios anuncioeditable = anuncioRepositoryImpl.recuperarAnuncio(idAnuncio);
		int oraLen = file.getOriginalFilename().length();
		logger.info("El nombre de la foto es: " + file.getOriginalFilename());

		for (int i = 0; i < oraLen; i++) {
			if (file.getOriginalFilename().charAt(i) == ' ') {
				model.addAttribute("faltaTitulo", "El nombre de la foto no puede tener espacios en blanco.");
				List<Anuncios> anuncios = anuncioRepositoryImpl.listAnuncio();

				model.addAttribute("listaAnuncios", anuncios);
				return "listaAnuncios";

			}
		}
		try {
			if (titulo.isEmpty()) {

				model.addAttribute("faltaTitulo",
						"El titulo en los anuncios es necesario, lo unico que se puede omitir es la foto.");
				List<Anuncios> anuncios = anuncioRepositoryImpl.listAnuncio();

				model.addAttribute("listaAnuncios", anuncios);
				return "listaAnuncios";
			}

			else if (anuncio.isEmpty()) {
				List<Anuncios> anuncios = anuncioRepositoryImpl.listAnuncio();

				model.addAttribute("listaAnuncios", anuncios);

				model.addAttribute("faltaAnuncio",
						"El texto en los anuncios es necesario, lo unico que se puede omitir es la foto.");
				return "listaAnuncios";
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date fechaConvertida = null;
				try {
					Date parsed = dateFormat.parse(fecha);
					fechaConvertida = new java.sql.Date(parsed.getTime());
				} catch (Exception e) {
					System.out.println("Error occurred" + e.getMessage());
				}

				anuncioeditable.setIdAnuncios(idAnuncio);
				anuncioeditable.setAnuncio(anuncio);
				anuncioeditable.setFecha(fechaConvertida);
				anuncioeditable.setTitulo_anuncio(titulo);
				if (!file.isEmpty()) {

					try {
						byte[] bytes = file.getBytes();
						Path rutaCompleta = Paths.get(rootPath + "//" + file.getOriginalFilename());
						Files.write(rutaCompleta, bytes);
						String extension = FilenameUtils.getExtension(file.getOriginalFilename());
						if(extension.equals("mp4")) {
							anuncioeditable.setVideo_anuncio(file.getOriginalFilename());
							
						}
						else if(extension.equals("jpg")||extension.equals("png")) {
							anuncioeditable.setFoto_anuncio(file.getOriginalFilename());
						}
						else {
							anuncioeditable.setAudio_anuncio(file.getOriginalFilename());
							
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				anuncioRepositoryImpl.editarAnuncio(anuncioeditable);

				List<Anuncios> anuncios = anuncioRepositoryImpl.listAnuncio();

				model.addAttribute("listaAnuncios", anuncios);

				model.addAttribute("anuncioSubido", "El anuncio se ha editado con exito.");

				return "listaAnuncios";
			}
		} catch (Exception e) {

			model.addAttribute("anuncioSubido", "El anuncio no se ha agregado.");

			logger.info("El error que da en /subirAnuncio es: " + e.getMessage());
		}
		List<Anuncios> anuncios = anuncioRepositoryImpl.listAnuncio();

		model.addAttribute("listaAnuncios", anuncios);
		return "listaAnuncios";

	}

	@GetMapping("/listaAnunciosAdmin")
	public String listaDeAnunciosAdministrador(Model model) {

		logger.info("Entramos en metodo /listaAnunciosAdmin");
		try {
			List<Anuncios> anuncios = anuncioRepositoryImpl.listAnuncio();

			model.addAttribute("listaAnuncios", anuncios);

			return "listaAnuncios";
		} catch (Exception e) {
			logger.info("El error que da en /listaAnunciosAdmin es: " + e.getMessage());
		}
		return "listaAnuncios";

	}

	@PostMapping("/borrarAnuncio")
	public String borrarAnuncio(@RequestParam("idAnuncio") int idAnuncio, Model model) {
		try {
			Anuncios anuncios = anuncioRepositoryImpl.recuperarAnuncio(idAnuncio);
			
			if (idAnuncio > 0 && anuncios != null) {
				anuncioRepositoryImpl.deleteAnuncio(idAnuncio);
			} else {
				return "listaAnuncios";
			}

		} catch (Exception e) {

			logger.info("El error que da en /borrarAnuncio es: " + e.getMessage());

		}

		List<Anuncios> anuncios;
		try {
			anuncios = anuncioRepositoryImpl.listAnuncio();
			model.addAttribute("listaAnuncios", anuncios);
		} catch (Exception e) {

			logger.info("El error que da en /borrarAnuncio recuperar todos es: " + e.getMessage());

		}

		return "listaAnuncios";

	}

	@PostMapping("/subirDocumento")
	public String insertarDocumento(@RequestParam("titulo") String titulo, @RequestParam("file") MultipartFile foto,
			Model model) throws IOException {

		logger.info("Entramos en metodo /Documento");

		logger.info("El nombre de la foto es: " + foto.getOriginalFilename());

		documentosRepositoryImpl.insertarDocumentos(titulo, foto);

		model.addAttribute("anuncioSubido", "El anuncio se ha agregado con exito.");

		return "fileUpload";

	}

	@GetMapping("/todosDocumentos")
	public String todosDocumento(Model model) throws IOException {

		logger.info("Entramos en metodo /Documento");

		List<Documentos> documento = new ArrayList<>();

		documento = documentosRepositoryImpl.listDocumentos();
		documento = documento.stream().sorted(Comparator.comparing(Documentos::getId).reversed())
				.collect(Collectors.toList());

		model.addAttribute("listaDocumentos", documento);

		return "documentos";

	}
	@GetMapping("/anuncio")
	public @ResponseBody Anuncios unanuncio(@RequestParam("idAnuncio")int idAnuncio){
		
		Anuncios anuncios = anuncioRepositoryImpl.recuperarAnuncio(idAnuncio);
		return anuncios;
		
	}

}
