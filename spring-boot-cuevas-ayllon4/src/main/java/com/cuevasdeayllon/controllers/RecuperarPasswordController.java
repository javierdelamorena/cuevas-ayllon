package com.cuevasdeayllon.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cuevasdeayllon.entity.Recuperar;
import com.cuevasdeayllon.entity.Usuario;
import com.cuevasdeayllon.repository.RecuperarRepository;
import com.cuevasdeayllon.service.UsuarioService;

@Controller
public class RecuperarPasswordController {

	private static final Logger logger = LoggerFactory.getLogger(RecuperarPasswordController.class);
	@Autowired
	private BCryptPasswordEncoder token;

	@Autowired
	private UsuarioService service;

	@Autowired
	private RecuperarRepository serviceRecuperar;

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping( path="/recuperarPassword")
	public String recuperarUsuario(@RequestParam("email") String  email,HttpServletRequest request,Model model) {


		Usuario usuario=new Usuario();
		logger.info("Este es el mail que recibimos: "+email);
		if(email!=null) {
			usuario=service.usuarioPorEmail(email);

			if(usuario==null) {
				logger.info("El usuario es  null");
				model.addAttribute("emailIncorrecto", "No encotramos el usuario con este mail en nuestro sistema, por favor, reviselo y vuelva a intentarlo.");
				return "recuperar";
			}else {

			}

			logger.info("El mail que recibimos es distinto de null y el usuario es: "+usuario.getNombre());
		}
		else {
			model.addAttribute("emailIncorrecto", "El campo email no puede estar vacio.");
		}


		if(email.equals(usuario.getEmail())) {
			Recuperar recupera=new Recuperar();
			logger.info("El mail de  usuario es igual al mail recibido: "+usuario.getEmail());
			String emailEncriptado = usuario.getEmail();
			recupera.setId_usuario(0);
			recupera.setId_usuario(usuario.getIdUsuario());
			recupera.setToken(token.encode(emailEncriptado));
			recupera.setEmail(usuario.getEmail());
			serviceRecuperar.insertar(recupera);
			serviceRecuperar.refrecar();
			List<Recuperar>recuperarLista=serviceRecuperar.todoeLosRecurar();
			for(Recuperar e:recuperarLista) {    
				if(e.getEmail().equals(email)) {
					logger.info("El token es distinto de null: "+recupera.getToken());
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setTo(e.getEmail());
					mailMessage.setSubject("Completa la actualizacion de contraseña!");
					mailMessage.setText("Para completar el proceso de actualizacion de contraseña, porfavor pulsa sobre este link: "
							//+ "http://localhost:8080/spring-boot-cuevas-ayllon4/confirm-reset?token="+e.getToken());
					+ "https://cuevas-de-ayllon.com/confirm-reset?token="+e.getToken());

					mailSender.send(mailMessage);
					logger.info("mandamos el mail");

				}
			}
		}
		else {
			model.addAttribute("emailIncorrecto", "El Email que ha introducido no es correcto.");
			return "recuperar";
		}

		model.addAttribute("emailIncorrecto", "Te hemos enviado un correo con un link para actualizar la contraseña, si no lo ves, mira en el correo no deseado o spam.");
		return "recuperar";

	}
	@GetMapping( path="/confirm-reset")
	public String recuperarToken(@RequestParam("token") String  token,HttpServletRequest request,Model model) {
		List<Recuperar>recuperarLista=new ArrayList<>();
		Recuperar recupera=new Recuperar();
		Usuario usuario=new Usuario();
		if(token!=null) {
			logger.info("El token es distinto de null");
			recupera =serviceRecuperar.recuperarPorTken(token);
		}
		else {
			model.addAttribute("emailIncorrecto", "Algo fue mal ,vuelva a intentarlo, perdone las molestias.");			
			return "recuperar";
		}

		if(recupera!=null) {
			recuperarLista=serviceRecuperar.todoeLosRecurar();
			for(Recuperar recuperaLista:recuperarLista) {
				if(recuperaLista.getToken().equals(token)) {
					usuario=service.usuarioPorId(recupera.getId_usuario());
					model.addAttribute("usuario", usuario);
					serviceRecuperar.borrarTodosRecuperar();
					
					return "editarContrasenia";
				}else {
					
					model.addAttribute("emailIncorrecto", "Vuelva a introducir el mail algo fue mal.");
					serviceRecuperar.borrarTodosRecuperar();
					return "recuperar";
				}
			}

		}//else {
			model.addAttribute("emailIncorrecto", "No encotramos este email en nuestro sistema, por favor, reviselo y vuelva a intentarlo.");
			return "recuperar";
		//}
	}
	@PostMapping(path="/editarUsuarioRecuperar")
	public String editarUsuario(Usuario usuario,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo editar");
		Usuario usu=new Usuario();
		logger.info("El usuario que recogemos es: "+usuario.getNombre()+" con el password  "+ usuario.getPassword()+" con idUsuario: "+usuario.getIdUsuario());
		//Usuario usuariocomprobacion=(Usuario) sesion.getAttribute("usuario");

		String passwordSinEncriptar=usuario.getPassword();


		String passwordEncriptada = usuario.getPassword();
		usu.setIdUsuario(usuario.getIdUsuario());
		usu.setNombre(usuario.getNombre());
		usu.setApellido1(usuario.getApellido1());
		usu.setApellido2(usuario.getApellido2());
		usu.setEmail(usuario.getEmail());
		usu.setDireccion(usuario.getDireccion());
		usu.setNotificacion("no");
		usu.setFoto("sinFoto.jpg");		
		usu.setPassword(token.encode(passwordEncriptada));
		service.editarUsuario(usu);


		Usuario usuari=service.usuarioPorNombre(usuario.getNombre());
		if(usuari!=null) {
			logger.info("Entramos en metodo editar Usuario y recogemos este usuario: "+usuari.getNombre());
			logger.info("entramos en metodo doVerificar y recogemos este password:["+usuari.getPassword()+"] nombre:["+usuari.getNombre()+"] email:["+usuari.getEmail()+"]");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(usuari.getEmail());
			message.setSubject(
					"Las contraseña de Cuevas de Ayllón ha sido actualizada ,  aqui te dejamos los datos de tu registro:");
			message.setText("Usuario: " + usuari.getNombre() +" Primer apellido: " + usuari.getApellido1() +" Segundo apellido: " + usuari.getApellido2() +" Direccion del pueblo: " + usuari.getDireccion() +  " password:" + passwordSinEncriptar + " email de registro:"
					+ usuari.getEmail());
			mailSender.send(message);
		}
		model.addAttribute("usuario", usuari);
		sesion.setAttribute("usuario", usuari);



		return "login";
	}

}
