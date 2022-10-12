package com.cuevasdeayllon.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cuevasdeayllon.entity.Usuario;


@Controller
public class IndexControler {
	
	private static final Logger logger=LoggerFactory.getLogger(IndexControler.class);

	@GetMapping({"/index","/home"})
	public String index() {

		logger.info("Entramos en metodo index");

		return "home";

	}
	@GetMapping("/registrarse")
	public String registro(Model model) {
		
		Usuario usuario=new Usuario();
		
		model.addAttribute("nombre","Nombre");
		model.addAttribute("apellido1","Apellido1");
		model.addAttribute("apellido2","Apellido2");
		model.addAttribute("email","Email");
		model.addAttribute("Password","Password");
		model.addAttribute("foto", "Foto");
		model.addAttribute("direccion", "Direccion en el pueblo");
		model.addAttribute("usuario", usuario);
		model.addAttribute("AltaUsuario", "Alta de Usuario");

		logger.info("Entramos en metodo index registro");

		return "registro";

	}
	@GetMapping("/toLoging")
	public String irALoging(Model model) {
		model.addAttribute("nombre","Nombre");
		model.addAttribute("Password","Password");

		logger.info("Entramos en metodo index");

		return "login";

	}
	@GetMapping("/galeriaFotografica")
	public String galerioa(Model model) {
		

		logger.info("Entramos en metodo galeriaFotografica");

		return "galeriafoto";

	}
	@GetMapping("/casasRurales")
	public String casas(Model model) {
		

		logger.info("Entramos en metodo /casasRurales");

		return "casasrurales";

	}
	@GetMapping("/historiaPueblo")
	public String historia(Model model) {
		

		logger.info("Entramos en metodo /historiaPueblo");

		return "historia";

	}
	@GetMapping("/tablonAnuncios")
	public String anuncios(Model model) {
		

		logger.info("Entramos en metodo /tablonAnuncios");

		return "tablonanuncios";

	}
	@GetMapping("/toRutas")
	public String rutas(Model model) {
		

		logger.info("Entramos en metodo /rutas");

		return "rutas";

	}

}
