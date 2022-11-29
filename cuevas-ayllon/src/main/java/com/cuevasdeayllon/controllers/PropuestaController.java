package com.cuevasdeayllon.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.primefaces.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cuevasdeayllon.dto.Objetos;
import com.cuevasdeayllon.entity.Comentarios;
import com.cuevasdeayllon.entity.Propuestas;
import com.cuevasdeayllon.entity.Puntuacion;
import com.cuevasdeayllon.entity.Usuario;
import com.cuevasdeayllon.service.ComentarioService;
import com.cuevasdeayllon.service.PropuestaService;
import com.cuevasdeayllon.service.PuntuacionService;
import com.cuevasdeayllon.service.UsuarioService;

@CrossOrigin(origins = "*")
@Controller
public class PropuestaController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	@Autowired 
	private PropuestaService propuestaService;
	@Autowired
	ComentarioService comentarioService;
	@Autowired
	UsuarioService usuarioservice;
	@Autowired
	PuntuacionService 	puntuacionservice;


	@PostMapping( value = ("/propuesta"), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Propuestas propuesta(@RequestParam("titulo")String titulo,@RequestParam("propuesta")String propuesta,Model model,HttpSession sesion) {

		Propuestas propuestaComprobar = new Propuestas();

		if(titulo!=null) {

			propuestaComprobar=propuestaService.findBtNombre(titulo);

		}

		if(propuestaComprobar==null) {
			Propuestas propuestas=new Propuestas();	
			propuestas.setPropuesta(propuesta);
			propuestas.setTitulo(titulo);
			logger.info("Entramos en metodo propuesta");
			Usuario usuario=(Usuario) sesion.getAttribute("usuario");
			System.out.println("el usuario es:"+usuario.getNombre());
			propuestas.setUsuario(usuario);
			propuestaService.save(propuestas);

			logger.info("propuestaRelizada", "La propuesta a sido realizada ncon exito");
			model.addAttribute("propuestaRelizada", "La propuesta a sido realizada con exito");
		}else {
			logger.info("propuestaExistente", "Esta propuiesta ya existe");
			model.addAttribute("propuestaExistente", "Esta propuesta ya existe");
		}



		return propuestaService.findBtNombre(titulo);

	}
	@GetMapping( value = ("/propuesta"), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Propuestas> todasPropuesta(Model model,HttpSession sesion) {



		return propuestaService.findAll();

	}



	@GetMapping("/comentarios")
	public String comentarios(@RequestParam("idPropuesta") int idpropuesta,Model model,HttpSession sesion){

		logger.info("Entramos en metodo comentarios esta es la idpropuesta"+idpropuesta);
		Objetos objetos=new Objetos();
		List<Comentarios> comentarios=comentarioService.findAllByIdPropuesta(idpropuesta);
		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		Propuestas propuesta=propuestaService.findByIdPropuesta(idpropuesta);

		logger.info("Estas son la propuestas que recogemos en metodo comentarios:["+propuesta.getPropuesta()+" "+propuesta.getIdPropuesta()+"]");
		
		model.addAttribute("comentarios", comentarios);

		comentarios.forEach(c ->logger.info("Estos son los usuarios: "+c.getUsuario().getNombre()));
	
		model.addAttribute("propuestas", propuesta);

		sesion.setAttribute("usuario", usuario);

		sesion.setAttribute("propuestas", propuesta);

		return "comentarios";

	}
	@GetMapping( value = ("salvarcomentario"), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Objetos comentario(@RequestParam("comentario")String comentario,Model model,HttpSession sesion) {
		Objetos objetos=new Objetos();
		Comentarios comentarios=new Comentarios();

		logger.info("Entramos en metodo salvarComentario y este es el comentario)"+comentario);
		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		Propuestas propuesta=(Propuestas) sesion.getAttribute("propuestas");
		List<Comentarios>lista=new ArrayList<Comentarios>();
		if(comentario!=null) {
			logger.info("Entramos en el metodo salvarcomentario la lista esta a 0 "+comentarios.getComentario());
			comentarios.setUsuario(usuario);
			comentarios.setPropuesta(propuesta);
			comentarios.setComentario(comentario);
			comentarioService.save(comentarios);
		}
		lista.add(comentarios);
		objetos.setComentarios(lista);
		objetos.setUsuario(usuario);
		objetos.setPropuestas(propuesta);

		logger.info("Este es el usuario= "+objetos.getUsuario().getNombre());
		logger.info("Esta es la propuesta= "+objetos.getPropuestas().getTitulo());
		logger.info("Esta es el comentario= "+objetos.getPropuestas().getTitulo());


		return objetos;

	}
	@GetMapping( value = ("todosComentarios"),produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public   List<Comentarios> todosComentario(HttpSession sesion) {
		List<String> comentariosUsuarios=new ArrayList<>();

		logger.info("Entramos en metodo todosComentario y este es el comentario)");

		Propuestas propuesta=(Propuestas) sesion.getAttribute("propuestas");

		List<Comentarios>comentarios=comentarioService.findAllByIdPropuesta(propuesta.getIdPropuesta());
		for(Comentarios e:comentarios) {
			comentariosUsuarios.add(e.getUsuario().getFoto().toString());
			comentariosUsuarios.add(e.getUsuario().getNombre().toString());		
			comentariosUsuarios.add(e.getComentario().toString());

			logger.info("Entramos en metodo todosComentario foto: "+e.getUsuario().getFoto().toString());

			logger.info("Entramos en metodo todosComentario nombre usuario: "+e.getUsuario().getNombre());

			logger.info("Entramos en metodo todosComentario comentario: "+e.getComentario());


		}
		
		return comentarios;

	}

	@ModelAttribute("comentarios")
	public List<Comentarios> listaPaises(HttpSession sesion){

		List<Comentarios> lista=(List<Comentarios>) sesion.getAttribute("comentarios");

		return lista;


	}
	@GetMapping(value = ("/puntuacionMas"))
	public String puntuacionMasUno(@RequestParam(required=false)String mas,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo /puntuacionMas con mas="+ mas);

		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		Propuestas propuesta=(Propuestas) sesion.getAttribute("propuestas");
		List<Comentarios> listaComentario=comentarioService.findAllByIdPropuesta(propuesta.getIdPropuesta());
		Puntuacion  puntuacion = new Puntuacion ();
		Puntuacion puntuacioncheck=puntuacionservice.puntuacionDePropuesta(usuario.getNombre(), propuesta.getTitulo());
		int contador=0 ;
		if(puntuacioncheck==null) {
			logger.info("puntuacioncheck= null "+ mas);
			contador =1;
			puntuacion.setPuntuacion(contador);
			puntuacion.setUsuario(usuario.getNombre());
			puntuacion.setPropuesta(propuesta.getTitulo());


			puntuacionservice.grabarPuntuacion(puntuacion);

		}
		else if(puntuacioncheck.getPuntuacion()==0||puntuacioncheck.getPuntuacion()==1) {
			logger.info("puntuacioncheck= 0 "+ mas);
			contador =1;
			puntuacion.setId_puntuacion(puntuacioncheck.getId_puntuacion());
			puntuacion.setPuntuacion(contador);
			puntuacion.setUsuario(usuario.getNombre());
			puntuacion.setPropuesta(propuesta.getTitulo());

			puntuacionservice.grabarPuntuacion(puntuacion);



		}

		List<Puntuacion> lista= puntuacionservice.listaDePuntos(propuesta.getTitulo());

		int totalPuntos=lista.stream().mapToInt(d->d.getPuntuacion()).sum();

		model.addAttribute("propuestas", propuesta);
		model.addAttribute("comentarios", listaComentario);
		model.addAttribute("usuario", usuario);
		model.addAttribute("puntuacion",totalPuntos);

		return "comentarios";



	}
	@GetMapping(value = ("/puntuacionMenos"))
	public String puntuacionMenosUno(@RequestParam(required=false)String menos,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo /puntuacionMas con mas="+ menos);

		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		Propuestas propuesta=(Propuestas) sesion.getAttribute("propuestas");
		List<Comentarios> listaComentario=comentarioService.findAllByIdPropuesta(propuesta.getIdPropuesta());
		Puntuacion  puntuacion = new Puntuacion ();
		Puntuacion puntuacioncheck=puntuacionservice.puntuacionDePropuesta(usuario.getNombre(), propuesta.getTitulo());
		int contador=0 ;
		if(puntuacioncheck==null) {
			logger.info("puntuacioncheck= null "+ menos);
			contador =0;
			puntuacion.setPuntuacion(contador);
			puntuacion.setUsuario(usuario.getNombre());
			puntuacion.setPropuesta(propuesta.getTitulo());


			puntuacionservice.grabarPuntuacion(puntuacion);

		}
		else if(puntuacioncheck.getPuntuacion()==1||puntuacioncheck.getPuntuacion()==0) {
			contador =0;
			logger.info("puntuacioncheck= 0 "+ menos);
			puntuacion.setId_puntuacion(puntuacioncheck.getId_puntuacion());
			puntuacion.setPuntuacion(contador);
			puntuacion.setUsuario(usuario.getNombre());
			puntuacion.setPropuesta(propuesta.getTitulo());

			puntuacionservice.grabarPuntuacion(puntuacion);



		}
		List<Puntuacion> lista= puntuacionservice.listaDePuntos(propuesta.getTitulo());

		int totalPuntos=lista.stream().mapToInt(d->d.getPuntuacion()).sum();



		model.addAttribute("propuestas", propuesta);
		model.addAttribute("comentarios", listaComentario);
		model.addAttribute("usuario", usuario);
		//model.addAttribute("puntuacion",totalPuntos);

		return "comentarios";



	}

	@GetMapping(value = "puntuacionTotal",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int  listaPuntosTotales(HttpSession sesion){
		logger.info("Entramos en metodo /puntuacionTotal ");
		int totalPuntos=0;
		if(sesion.getAttribute("propuestas")!=null) {
			Propuestas propuesta=(Propuestas) sesion.getAttribute("propuestas");

			List<Puntuacion> lista= puntuacionservice.listaDePuntos(propuesta.getTitulo());

			return totalPuntos=lista.stream().mapToInt(d->d.getPuntuacion()).sum();
		}

		return totalPuntos;
	}


}
