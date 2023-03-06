package com.cuevasdeayllon.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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

	@Autowired
	private JavaMailSender mailSender;

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);


	@PostMapping( path= "/registrar")
	public String registrarUsuario(@Valid Usuario usuario,BindingResult result,@RequestParam("file")MultipartFile foto,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo registrar");
		logger.info("El usuario que recogemos es: "+usuario.getNombre()+" con el password  "+ usuario.getPassword());
		Usuario usuariocomprobacion=null;
		String passwordSinEncriptar=usuario.getPassword();


		try {

			if( usuario!=null) {
				logger.info("El usuario es distinto de null");
				usuariocomprobacion=service.usuarioPorNombre(usuario.getNombre());
			}

			if(usuariocomprobacion!=null ) {

				model.addAttribute("usuarioExiste", "El nombre ya existe registrese con otro nombre o añada una letra al final o si es compuesto pruebe solo con uno");

				logger.info("Entramos en metodo registrar comprobamos si existe usuario por nombre Usuario");

				return "registro";

			}
			if( usuario!=null) {
				logger.info("El usuario es distinto de null");
				usuariocomprobacion=service.usuarioPorEmail(usuario.getEmail());
			}

			if(usuariocomprobacion!=null ) {
				model.addAttribute("usuarioExiste", "El email ya existe registrese con otro email ");

				logger.info("Entramos en metodo registrar Usuario comprobamos email");

				return "registro";
			}


			String rootPath="/uploads/";
			//String rootPath="C://TEMP//uploads";

			if(!foto.isEmpty()&&usuario!=null) {

				int oraLen = foto.getOriginalFilename().length();
				logger.info("El nombre de la foto es: "+foto.getOriginalFilename());

				for (int i = 0; i <  oraLen; i++) {
					if (foto.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("usuarioExiste", "El nombre de la foto no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						return "registro";

					}
				}


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
					if(usuari!=null) {
						logger.info("Entramos en metodo registrar Usuario y recogemos este usuario: "+usuari.getNombre());
						logger.info("entramos en metodo doVerificar y recogemos este password:["+usuari.getPassword()+"] nombre:["+usuari.getNombre()+"] email:["+usuari.getEmail()+"]");
						SimpleMailMessage message = new SimpleMailMessage();
						message.setTo(usuari.getEmail());
						message.setSubject(
								"Muchas gracias por registrarte en el espacio vecinal de Cuevas de Ayllón,  aqui te dejamos los datos de tu registro:");
						message.setText("Usuario: " + usuari.getNombre() +" Primer apellido: " + usuari.getApellido1() +" Segundo apellido: " + usuari.getApellido2() +" Direccion del pueblo: " + usuari.getDireccion() +  " password:" + passwordSinEncriptar + " email de registro:"
								+ usuari.getEmail());
						mailSender.send(message);
					}
					model.addAttribute("usuario", usuari);
					
					return "login";

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else {

				usuario.setFoto("sinFoto.jpg");
				String passwordEncriptada = usuario.getPassword();
				usuario.setPassword(passwordEncoder.encode(passwordEncriptada));
				service.salvarUsuario(usuario);


				Usuario usuari=service.usuarioPorNombre(usuario.getNombre());
				if(usuari!=null) {
					logger.info("Entramos en metodo registrar Usuario y recogemos este usuario: "+usuari.getNombre());
					logger.info("entramos en metodo doVerificar y recogemos este password:["+usuari.getPassword()+"] nombre:["+usuari.getNombre()+"] email:["+usuari.getEmail()+"]");
					SimpleMailMessage message = new SimpleMailMessage();
					message.setTo(usuari.getEmail());
					message.setSubject(
							"Muchas gracias por registrarte en el espacio vecinal de Cuevas de Ayllón,  aqui te dejamos los datos de tu registro:");
					message.setText("Usuario: " + usuari.getNombre() +" Primer apellido: " + usuari.getApellido1() +" Segundo apellido: " + usuari.getApellido2() +" Direccion del pueblo: " + usuari.getDireccion() +  " password:" + passwordSinEncriptar + " email de registro:"
							+ usuari.getEmail());
					mailSender.send(message);
				}
				model.addAttribute("usuario", usuari);
				sesion.setAttribute("usuario", usuari);
				return "login";

			}
		}catch(javax.validation.ConstraintViolationException e) {

			return "registro";

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
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
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
	/**
	 * 
	 * @param idUsuario
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping( path="/doBorrarDesdeLista")
	public String borrarUsuario(@RequestParam("idUsuario") int  idUsuario,HttpServletRequest request,Model model) {

		Usuario usuario=service.usuarioPorId(idUsuario);
		if(usuario!=null) {

			service.borrarUsuario(usuario);
		}

		model.addAttribute("usuarioBorrado", "El suario se ha borrado con exito");

		return "todosLosUsuarios";


	}
	@PostMapping( path="/doBorrarDesdeUsuario")
	public String borrarUsuarioDesdeUsuario(@RequestParam("idUsuario") int  idUsuario,HttpServletRequest request,Model model) {

		Usuario usuario=service.usuarioPorId(idUsuario);
		if(usuario!=null) {

			service.borrarUsuario(usuario);
		}

		model.addAttribute("usuarioBorrado", "El suario se ha borrado con exito");

		return "home";


	}
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping( path="/todosUsuarios")
	public String todossuario(HttpServletRequest request,Model model) {

		List<Usuario>todos=service.todosLosUsuarios();

		model.addAttribute("todosLosusuarios", todos);

		return "todosLosUsuarios";


	}
	//	@GetMapping(value = "/unUsuarioSesion", produces = MediaType.APPLICATION_JSON_VALUE)
	//	public String  unUsuarioAlta(@RequestParam String nombre, HttpSession sesion,
	//			HttpServletResponse response) {
	//		System.out.println("esta es la sesion"+sesion);
	//		Usuario usuarioSesion =service.usuarioPorNombre(nombre);
	//		sesion.setAttribute("usuario", usuarioSesion);
	//
	//		logger.info("Entramos en unUsuarioSesio este es el usuario que recojemos: [ nombre " + usuarioSesion.getNombre()
	//		+ " idUsuario:  " + usuarioSesion.getIdUsuario() +  " role: " + usuarioSesion.getRoles() + "]");
	//		return "redirect:/usuario";
	//
	//
	//
	//	}
	@GetMapping("/login_failure_handler")
	public String loginFailureHandler(Model model) {

		model.addAttribute("loginIncorrecto", "El nombre o la contraseña no son correctos, inténtelo de nuevo");
		logger.info("Login failure handler....");

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
	/**
	 *  Recibe una llamada con la password para recibirla sin encriptar y manda un mail con los datos recibidos en el registro
	 * @param password
	 * @param sesion
	 * @param model
	 * @return
	 * @throws Exception
	 */
	//	@GetMapping(value="/doVerificar", produces = MediaType.APPLICATION_JSON_VALUE)
	//	public void enviar(@RequestParam(value="password",required =true) String password,@RequestParam(value="nombre",required = true) String nombre,@RequestParam(value="email",required=true) String email,  HttpSession sesion, Model model) throws Exception {
	//		logger.info("entramos en metodo doVerificar y recogemos este password:["+password+"] nombre:["+nombre+"] email:["+email+"]");
	//		SimpleMailMessage message = new SimpleMailMessage();
	//		message.setTo(email);
	//		message.setSubject(
	//				"Muchas Gracias por registrarte en el espacio vecinal de cuevas de ayllopn,  aqui te dejamos los datos de tu registro:");
	//		message.setText("Usuario: " + nombre + " password:" + password + " email de registro:"
	//				+ email);
	//		mailSender.send(message);


	//return "login";
	//	}

	@PostMapping(path="/editarUsuario")
	public String editarUsuario(@Valid Usuario usuario,BindingResult result,@RequestParam("file")MultipartFile foto,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo editar");

		logger.info("El usuario que recogemos es: "+usuario.getNombre()+" con el password  "+ usuario.getPassword()+" con idUsuario: "+usuario.getIdUsuario());
		//Usuario usuariocomprobacion=(Usuario) sesion.getAttribute("usuario");

		String passwordSinEncriptar=usuario.getPassword();

		if(!foto.isEmpty()&&usuario!=null) {
			Usuario usuari=service.usuarioPorNombre(usuario.getNombre());
			int oraLen = foto.getOriginalFilename().length();
			logger.info("El nombre de la foto es: "+foto.getOriginalFilename());

			for (int i = 0; i <  oraLen; i++) {
				if (foto.getOriginalFilename().charAt(i) == ' ') {
					model.addAttribute("usuario", usuari);
					model.addAttribute("fotoConHuecos", "Lo sentimos, el nombre de la foto no puede tener espacios en blanco, cambie el nombre de la foto y añadala de nuevo, gracias.");
					return "usuario";

				}
			}

			String rootPath="/uploads/";
			//String rootPath="C://TEMP//uploads";
			try {
				byte[]bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+foto.getOriginalFilename());
				logger.info("Esta es la ruta absoluta="+rutaCompleta.toAbsolutePath());
				Files.write(rutaCompleta,bytes);
				usuario.setFoto(foto.getOriginalFilename());
				String passwordEncriptada = usuario.getPassword();
				usuario.setPassword(passwordEncoder.encode(passwordEncriptada));
				usuario.setIdUsuario(usuario.getIdUsuario());
				service.editarUsuario(usuario);


				usuari=service.usuarioPorNombre(usuario.getNombre());
				if(usuari!=null) {
					logger.info("Entramos en metodo registrar Usuario y recogemos este usuario: "+usuari.getNombre());
					logger.info("entramos en metodo doVerificar y recogemos este password:["+usuari.getPassword()+"] nombre:["+usuari.getNombre()+"] email:["+usuari.getEmail()+"]");
					SimpleMailMessage message = new SimpleMailMessage();
					message.setTo(usuari.getEmail());
					message.setSubject(
							"El usuario ha sido editado correctamente,  aqui te dejamos los datos de tu registro:");
					message.setText("Usuario: " + usuari.getNombre() +" Primer apellido: " + usuari.getApellido1() +" Segundo apellido: " + usuari.getApellido2() +" Direccion del pueblo: " + usuari.getDireccion() +  " password:" + passwordSinEncriptar + " email de registro:"
							+ usuari.getEmail());
					mailSender.send(message);
				}
				model.addAttribute("usuario", usuari);
				sesion.setAttribute("usuario", usuari);


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
			//				byte[]bytes="sinFoto.jpg".getBytes();
			//				Path rutaCompleta=Paths.get(rootPath+"//"+"sinFoto.jpg");
			//				logger.info("Esta es la ruta absoluta="+rutaCompleta.toAbsolutePath());
			//				Files.write(rutaCompleta,bytes);
			usuario.setFoto("sinFoto.jpg");
			String passwordEncriptada = usuario.getPassword();
			usuario.setIdUsuario(usuario.getIdUsuario());
			usuario.setPassword(passwordEncoder.encode(passwordEncriptada));
			service.editarUsuario(usuario);


			Usuario usuari=service.usuarioPorNombre(usuario.getNombre());
			if(usuari!=null) {
				logger.info("Entramos en metodo registrar Usuario y recogemos este usuario: "+usuari.getNombre());
				logger.info("entramos en metodo doVerificar y recogemos este password:["+usuari.getPassword()+"] nombre:["+usuari.getNombre()+"] email:["+usuari.getEmail()+"]");
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(usuari.getEmail());
				message.setSubject(
						"Muchas gracias por registrarte en el espacio vecinal de Cuevas de Ayllón,  aqui te dejamos los datos de tu registro:");
				message.setText("Usuario: " + usuari.getNombre() +" Primer apellido: " + usuari.getApellido1() +" Segundo apellido: " + usuari.getApellido2() +" Direccion del pueblo: " + usuari.getDireccion() +  " password:" + passwordSinEncriptar + " email de registro:"
						+ usuari.getEmail());
				mailSender.send(message);
			}
			model.addAttribute("usuario", usuari);
			sesion.setAttribute("usuario", usuari);


		}
		return "usuario";
	}
	// Controlador que se encarga de mandar un mail desde contacto al email de la
	// empresa
	@PostMapping("/doContacto")
	public String contacto(@RequestParam("nombre") String nombre, @RequestParam("email") String email,
			@RequestParam("mensaje") String mensaje, HttpSession sesion,Model model) throws Exception {

		logger.info("estamos en contacto recibimos nombre: "+nombre+" email: "+ email+" mensaje: "+ mensaje);

		if(nombre!=""&&email!=""&&mensaje!="") {
			Usuario usuario=service.usuarioPorEmail(email);
			if(usuario!=null) {


				logger.info("estamos en contacto no hay ningun campo vacio");
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo("ayuntamientodecuevas@cuevas-de-ayllon.com");
				message.setSubject("Mesaje de usuario: " +nombre );
				message.setText("Usuario: " +nombre +" con email de registro: "
						+ email + "  mensaje del cliente:" + mensaje);
				mailSender.send(message);
				model.addAttribute("camposVacios","El mensaje se ha enviado con exito, te contestaremos a la mayor brevedad posible.");
			}else
			{
				logger.info("estamos en contacto el mail no esta en el sistema");
				model.addAttribute("camposVacios","El mail no esta en nuestro sistema, intentelo de nuevo, sentimos la molestia.");
			}
		}else {
			logger.info("estamos en contacto  hay campos vacio");
			model.addAttribute("camposVacios","Se tienen que rellenar todos los campos, para poder responderte y saber quien nos esta escribiendo.");
			return "contactoMensaje";
		}

		return "contactoMensaje";

	}

	@GetMapping("/login")
	public String login(Model model,Principal principal,HttpSession session) {


		if(principal!=null) {
			Usuario usuario=service.usuarioPorNombre(principal.getName());

			session.setAttribute("usuario", usuario);

			logger.info("Entramos en metodo login  recogemos este usuario: "+usuario.getNombre());

			return "redirect:/usuario";
		}else {
			
			model.addAttribute("loginIncorrecto","El usuario o la contraseña son incorrectos.Recuerda que es el nombre no el mail");
			return "login";
			
		}
		

		



	}	


}
