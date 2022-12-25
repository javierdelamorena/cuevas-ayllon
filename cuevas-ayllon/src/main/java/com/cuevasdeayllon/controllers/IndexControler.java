package com.cuevasdeayllon.controllers;

import javax.servlet.http.HttpSession;

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

		logger.info("Entramos en metodo index/registrarse");
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

		

		return "registro";

	}
	@GetMapping("/toLoging")
	public String irALoging(Model model) {
		
		model.addAttribute("nombre","Nombre");
		model.addAttribute("Password","Password");

		logger.info("Entramos en metodo index/toLoging");

		return "login";

	}
	@GetMapping("/galeriaFotografica")
	public String galerioa(Model model) {


		logger.info("Entramos en metodo galeriaFotografica");

		return "galeriafoto";

	}
	@GetMapping("/orfeo")
	public String casas(Model model) {


		logger.info("Entramos en metodo /casasRurales");

		return "campusOrfeo";

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
	@GetMapping("/enlacesDeInteres")
	public String enlacesDeInteres(Model model) {


		logger.info("Entramos en metodo /enlacesDeInteres");

		return "enlacesDeInteres";

	}
	@GetMapping("/subirFotos")
	public String subirFoto(Model model,HttpSession sesion) {

		Usuario usuario=new Usuario();
		try {
			if(sesion.getAttribute("usuario")!=null) {
				usuario=(Usuario) sesion.getAttribute("usuario");
				model.addAttribute("usuario", usuario);
				
				logger.info("Entramos en metodo index subirFoto el idUsuario es: "+usuario.getIdUsuario());

				logger.info("Entramos en metodo index subirFoto");

				return "subirFoto";
			}
		}catch(Exception e) {

			logger.info("El error que da en index//subirFotos es: "+e.getMessage());


		}
		return "login";
	}
	@GetMapping("/anuncios")
	public String subirAnuncio(Model model,HttpSession sesion) {
		logger.info("Entramos en metodo index subirFoto");
		Usuario usuario=new Usuario();
		try {
			if(sesion.getAttribute("usuario")!=null) {
				usuario=(Usuario) sesion.getAttribute("usuario");
				model.addAttribute("titulo", "Titulo");
				model.addAttribute("anuncio", "Anuncio");
				model.addAttribute("foto", "Documento");
				logger.info("Entramos en metodo index subirFoto el idUsuario es: "+usuario.getIdUsuario());
				return "subirAnuncio";
			}
			else {
				logger.info("La sesion en /toUsuario esta vacia");
			}

		}catch(Exception e) {

			logger.info("El error que da en index/anuncios es: "+e.getMessage());

		}
		return "login";
	}
	@GetMapping("/toUsuario")
	public String usuario(Model model,HttpSession sesion) {
		
		logger.info("Entramos en metodo /toUsuario");

		try {
			if(sesion.getAttribute("usuario")!=null) {
				Usuario usuario=(Usuario) sesion.getAttribute("usuario");
				logger.info("Este es el password:"+usuario.getPassword());
				model.addAttribute("usuario", usuario);		
				return "usuario";
			}else {
				logger.info("La sesion en /toUsuario esta vacia");
				
				return "login";
			}
		}catch(Exception e) {
			logger.info("El error que da en index/toUsuario es: "+e.getMessage());
		}
		return "usuario";

	}
	@GetMapping("/preguntas")
	public String preguntas(Model model,HttpSession sesion) {


		logger.info("Entramos en metodo /preguntas");

		return "preguntasFrecuentes";

	}
	@GetMapping("/contacto")
	public String contacto(Model model,HttpSession sesion) {


		logger.info("Entramos en metodo /contacto");

		return "contactoMensaje";

	}
	@GetMapping("/recuperar")
	public String recuperar(Model model,HttpSession sesion) {


		logger.info("Entramos en metodo index/recuperar");

		return "recuperar";

	}
	
	


}
