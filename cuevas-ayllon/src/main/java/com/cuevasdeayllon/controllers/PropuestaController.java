package com.cuevasdeayllon.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cuevasdeayllon.entity.Comentarios;
import com.cuevasdeayllon.entity.Propuestas;
import com.cuevasdeayllon.entity.Usuario;
import com.cuevasdeayllon.service.ComentarioService;
import com.cuevasdeayllon.service.PropuestaService;
import com.cuevasdeayllon.service.UsuarioService;

@Controller
public class PropuestaController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	@Autowired 
	private PropuestaService propuestaService;
	@Autowired
	ComentarioService comentarioService;
	@Autowired
	UsuarioService usuarioservice;


	@PostMapping( value = ("/propuesta"), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Propuestas propuesta(@RequestParam("titulo")String titulo,@RequestParam("propuesta")String propuesta,Model model,HttpSession sesion) {
		Propuestas propuestas=new Propuestas();
		propuestas.setPropuesta(propuesta);
		propuestas.setTitulo(titulo);
		logger.info("Entramos en metodo propuesta");
		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		System.out.println("el usuario es:"+usuario.getNombre());
		propuestas.setUsuario(usuario);


		propuestaService.save(propuestas);


		return propuestaService.findBtNombre(titulo);

	}
	@GetMapping( value = ("/propuesta"), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Propuestas> todasPropuesta(Model model,HttpSession sesion) {



		return propuestaService.findAll();

	}

	@GetMapping("/comentarios")
	public String comentarios(@RequestParam("idPropuesta") int idpropuesta,Model model,HttpSession sesion){

		logger.info("Entramos en metodo comentarios esta es la idpropuesta"+idpropuesta);

		List<Comentarios> comentarios=comentarioService.findAll(idpropuesta);
		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		Propuestas propuesta=propuestaService.findByIdPropuesta(idpropuesta);

		logger.info("Estas son la propuestas que recogemos en metodo comentarios:["+propuesta.getPropuesta()+" "+propuesta.getIdPropuesta()+"]");

		model.addAttribute("comentarios", comentarios);

		model.addAttribute("propuestas", propuesta);
		
		model.addAttribute("usuario", usuario);

		sesion.setAttribute("propuestas", propuesta);

		return "comentarios";

	}
	@GetMapping( value = ("/salvarcomentario"))
//	public String comentario(@RequestParam("comentario")String comentario,Model model,HttpSession sesion) {
	public String comentario(String comentario,Model model,HttpSession sesion) {
         Comentarios comentarios=new Comentarios();
		logger.info("Entramos en metodo sslvarcomentario y este es el comentarios.getComentario()"+comentarios.getComentario());
		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		Propuestas propuesta=(Propuestas) sesion.getAttribute("propuestas");
		if(comentarios!=null) {
			logger.info("Entramos en el metodo salvarcomentario primer if y recogemos este comentario "+comentarios.getComentario());
			comentarios.setUsuario(usuario);
			comentarios.setPropuesta(propuesta);
			comentarios.setComentario(comentario);
			comentarioService.save(comentarios);

		}
		List<Comentarios> listaComentario=comentarioService.findAll(propuesta.getIdPropuesta());
		sesion.setAttribute("comentarios", listaComentario);
		model.addAttribute("comentarios", listaComentario);
		model.addAttribute("propuestas", propuesta);

		return "comentarios";

	}
	@ModelAttribute("comentarios")
	public List<Comentarios> listaPaises(HttpSession sesion){

		List<Comentarios> lista=(List<Comentarios>) sesion.getAttribute("comentarios");

		return lista;


	}



}
