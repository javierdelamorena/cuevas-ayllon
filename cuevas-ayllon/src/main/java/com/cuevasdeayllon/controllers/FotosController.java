package com.cuevasdeayllon.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Fotos;
import com.cuevasdeayllon.repository.FotosRepositoryImpl;

@Controller
public class FotosController {

	private static final Logger logger = LoggerFactory.getLogger(FotosController.class);

	@Autowired
	FotosRepositoryImpl service;

	@GetMapping( value = ("/fotosGaleria"), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Fotos> todasFotos(Model model,HttpSession sesion) {

		List<Fotos>fotos=service.todasLasFotos();
		
		fotos.forEach(f->logger.info("Esta es la lista de fotos: "+f.getFotos()+" y el usuario"+ f.getId_usuario()));

		return fotos;

	}
	@GetMapping( path= "/fotosGaleriaLista")
	public String todasFotosLista(Model model,HttpSession sesion) {

		List<Fotos>fotos=service.todasLasFotos();
		
		fotos.forEach(f->logger.info("Esta es la lista de fotos: "+f.getFotos()+" y el usuario"+ f.getId_usuario()));
		model.addAttribute("fotosLista", fotos);
		return "listaFotos";

	}



	@PostMapping( path= "/salvarFoto")
	public String registrarUsuario(@RequestParam("idUsuario") int id_usuario,@RequestParam("file")MultipartFile foto,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo salvarFoto");
		logger.info("El usuario que recogemos es: "+id_usuario+" con la foto  "+ foto);

		Fotos fotos=new Fotos();
		
		//String rootPath="/uploadsGaleria/";
		String rootPath="C://TEMP//uploadsGaleria";

		if(!foto.isEmpty()&id_usuario>0) {


			try {
				byte[]bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+foto.getOriginalFilename());
				logger.info("Esta es la ruta absoluta="+rutaCompleta.toAbsolutePath());
				Files.write(rutaCompleta,bytes);
				fotos.setIdFotos(0);
				fotos.setFotos(foto.getOriginalFilename());
				fotos.setId_usuario(id_usuario);

				service.salvarFoto(fotos);
				
				return "login";

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}
		return "galeriafoto";
	}
	
	@PostMapping("/borrarFoto")	
	public String borrarFoto(@RequestParam("fotos")String foto,@RequestParam("id_usuario")int id_usuario,@RequestParam("idFotos")int idFotos,Model model) {
		Fotos fotos=new Fotos();
		
		if(foto!=null) {
			fotos.setFotos(foto)	;
			fotos.setId_usuario(id_usuario);
			fotos.setIdFotos(idFotos);
			
		service.deleteFoto(fotos);
		model.addAttribute("fotoBorrada", "La foto se ha borrado con exito.");
		}
		List<Fotos>fotosLista=service.todasLasFotos();
		model.addAttribute("fotosLista", fotosLista);
		return "listaFotos";
	}
	
}
