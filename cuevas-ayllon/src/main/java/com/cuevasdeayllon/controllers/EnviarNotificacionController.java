package com.cuevasdeayllon.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cuevasdeayllon.entity.Usuario;
import com.cuevasdeayllon.service.UsuarioService;

@Controller
public class EnviarNotificacionController {

	private static final Logger logger = LoggerFactory.getLogger(EnviarNotificacionController.class);


	@Autowired
	private UsuarioService service;

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping(value = ("/enviarNotificacion"), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Usuario> listaDeAnuncios( Model model) {

		logger.info("Entramos en metodo /enviarNotificacion");

		List<Usuario>usuarioList=service.todosLosUsuarios();

		usuarioList.forEach(u->logger.info(""+u.getNombre()));
		for(Usuario usu:usuarioList) {
			if(usu.getNotificacion().equals("si")) {
				logger.info("Entramos en metodo registrar Usuario y recogemos este usuario: "+usu.getNombre());
				logger.info("entramos en metodo doVerificar y recogemos este password:["+usu.getPassword()+"] nombre:["+usu.getNombre()+"] email:["+usu.getEmail()+"]");
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(usu.getEmail());
				message.setSubject(
						"Hola vecino, se ha realizado una propuesta nueva en el espacio vecinal de Cuevas de Ayllon.");
				message.setText("Te informamos que un vecino ha realizado una propuesta en el espacio vecinal de Cuevas de Ayllón,"
						+ " si te interesa saber que propuesta es ,entra en el espacio vecinal y podras dar tu opinion y votarla. Ya sabes que si quieres dejar de recibir emails como este,"
						+ " solo tienes que entrar en tu espacio vecinal, "
						+ "pulsar en recibir notificaciones y seguidamente pulsar en dejar de recibir notificaciones.  Muchas gracias y un saludo.");
				mailSender.send(message);
			}
		}


		return usuarioList;



	}
	@PostMapping(path="/recibirNotificacion")
	public String recibirNotificacion(@RequestParam("idUsuario")int idUsuario,Model model,HttpSession sesion) {


		logger.info("Entramos en metodo recibirNotificacion");

		logger.info("El idusuario que recogemos es: "+idUsuario);


		Usuario usuari=service.usuarioPorId(idUsuario);


		usuari.setNotificacion("si");

		service.editarUsuario(usuari);
		model.addAttribute("usuario", usuari);




		return "usuario";
	}
	@PostMapping(path="/noRecibirNotificacion")
	public String noRecibirNotificacion(@RequestParam("idUsuario")int idUsuario,Model model,HttpSession sesion) {


		logger.info("Entramos en metodo  no recibirNotificacion");

		logger.info("El idusuario que recogemos es: "+idUsuario);


		Usuario usuari=service.usuarioPorId(idUsuario);

		usuari.setNotificacion("no");

		service.editarUsuario(usuari);




		model.addAttribute("usuario", usuari);




		return "usuario";
	}

}
