package com.cuevasdeayllon.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Usuario;
import com.cuevasdeayllon.service.UsuarioService;
//@SessionAttributes("usuario")
@Controller
public class UsuarioController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	UsuarioService service;

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@PostMapping( path= "/registrar")
	public String registrarUsuario(@ModelAttribute("usuario")Usuario usuario,@RequestParam("file")MultipartFile foto,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo registrar");
		logger.info("El usuario que recogemos es: "+usuario.getNombre()+" con el password  "+ usuario.getPassword());
		Usuario usuariocomprobacion=null;
		if( usuario!=null) {
			logger.info("El usuario es distinto de null");
			usuariocomprobacion=service.usuarioPorNombre(usuario.getNombre());
		}
		if(usuariocomprobacion!=null ) {

			model.addAttribute("usuarioExiste", "El nombre ya existe registrese con otro nombre añada una letra al final o si es compuesto pruebe solo con uno");

			logger.info("Entramos en metodo registrar Usuario");

			return "registro";

		}

		if(!foto.isEmpty()&&usuario!=null) {

			
			String rootPath="/uploads**/";
			try {
				byte[]bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+foto.getOriginalFilename());
				logger.info("Esta es la ruta absoluta="+rutaCompleta.toAbsolutePath());
				Files.write(rutaCompleta,bytes);
				usuario.setFoto(foto.getOriginalFilename());
				String passwordEncriptada = usuario.getPassword();
				usuario.setPassword(passwordEncoder.encode(passwordEncriptada));
				service.salvarUsuario(usuario);

				Usuario usuari=service.usuarioPorNombre(usuario.getNombre());
				logger.info("Entramos en metodo registrar Usuario y recogemos este"+usuari);
				model.addAttribute("usuario", usuari);
				sesion.setAttribute("usuario", usuari);
				return "login";

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


		return "registro";

	}
	//	@GetMapping("/login")
	//	public String loging(@RequestParam(value = "error", required = false) String error, HttpSession sesion,
	//			Principal principal, Model model) throws ParseException{
	//	
	//		logger.info("Entramos en metodo loging");
	//		
	//		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
	//		
	//		logger.info("Recogemos este usuario["+usuario+"]");
	//		
	//		
	//		model.addAttribute("usuario", usuario);
	//		
	//		return "usuario";
	//		
	//	}
	@GetMapping( path="/usuario")
	public String usuario(HttpServletRequest request,Model model) {
		HttpSession sesion=request.getSession(true); 
		logger.info("Entramos en metodo usuario");
      if(sesion.getAttribute("usuario")!=null) {
		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		logger.info("Recogemos este usuario["+usuario+"]");

		model.addAttribute("usuario", usuario);

		return "usuario";
      }else {
    	  logger.info("La sesion esta a null y no se ha creado");
    	  return "/login";
      }

	}
	@GetMapping(value = "/unUsuarioSesion", produces = MediaType.APPLICATION_JSON_VALUE)
	public String  unUsuarioAlta(@RequestParam String nombre, HttpSession sesion,
			HttpServletResponse response) {
		System.out.println("esta es la sesion"+sesion);
		Usuario usuarioSesion =service.usuarioPorNombre(nombre);
		sesion.setAttribute("usuario", usuarioSesion);

		logger.info("Entramos en unUsuarioSesio este es el usuario que recojemos: [ nombre " + usuarioSesion.getNombre()
		+ " idUsuario:  " + usuarioSesion.getIdUsuario() +  " role: " + usuarioSesion.getRoles() + "]");
		return "redirect:/usuario";



	}
	@PostMapping("/login_failure_handler")
	public String loginFailureHandler(Model model) {

		model.addAttribute("loginIncorrecto", "El nombre o la contraseña no son correctos intentelo de nuevo");
		System.out.println("Login failure handler....");

		return "redirect:/login";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
		@RequestMapping(value = "/logout", method = RequestMethod.POST)
		public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "redirect:/login";// para redirigir a la pantalla de login
		}

}
