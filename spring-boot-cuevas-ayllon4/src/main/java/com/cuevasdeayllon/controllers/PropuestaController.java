package com.cuevasdeayllon.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.primefaces.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cuevasdeayllon.dto.ComentariosDto;
import com.cuevasdeayllon.dto.Objetos;
import com.cuevasdeayllon.entity.Anuncios;
import com.cuevasdeayllon.entity.Comentarios;
import com.cuevasdeayllon.entity.Propuestas;
import com.cuevasdeayllon.entity.Puntuacion;
import com.cuevasdeayllon.entity.PuntuacionTotal;
import com.cuevasdeayllon.entity.Usuario;
import com.cuevasdeayllon.repository.PuntuacionTotalRepository;
import com.cuevasdeayllon.service.ComentarioService;
import com.cuevasdeayllon.service.PropuestaService;
import com.cuevasdeayllon.service.PuntuacionService;
import com.cuevasdeayllon.service.UsuarioService;
import com.cuevasdeayllon.utilidades.Utilidades;

@CrossOrigin(origins = "*")
@Controller
public class PropuestaController {

	private static final Logger logger = LoggerFactory.getLogger(PropuestaController.class);
	@Autowired 
	private PropuestaService propuestaService;
	@Autowired
	private ComentarioService comentarioService;
	@Autowired
	private UsuarioService usuarioservice;
	@Autowired
	private PuntuacionService 	puntuacionservice;

	@Autowired
	private PuntuacionTotalRepository puntuacionTotalservice; 
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Metodo que se utiliza para salvar una propuesta
	 * @param titulo
	 * @param propuesta
	 * @param model
	 * @param sesion
	 * @return
	 */

	@PostMapping( value = ("/propuesta"), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Propuestas propuesta(@RequestParam("titulo")String titulo,@RequestParam("propuesta")String propuesta,Model model,HttpSession sesion) {

		Propuestas propuestaComprobar = new Propuestas();

		if(titulo!=null) {

			propuestaComprobar=propuestaService.findBtNombre(titulo);

		}

		if(propuestaComprobar==null) {

			Propuestas propuestas=new Propuestas();	
			propuestas.setFecha(new Date());
			propuestas.setPropuesta(propuesta);
			propuestas.setTitulo(titulo);
			logger.info("Entramos en metodo propuesta");
			Usuario usuario=(Usuario) sesion.getAttribute("usuario");
			System.out.println("el usuario es:"+usuario.getNombre());
			propuestas.setUsuario(usuario);
			propuestaService.save(propuestas);
			List<Usuario> usuari=usuarioservice.todosLosUsuarios();

			//				for(Usuario us:usuari) {
			//				
			//				logger.info("mandamos emails masivos:");
			//				SimpleMailMessage message = new SimpleMailMessage();
			//				message.setTo(us.getEmail());
			//				message.setSubject("Se ha realizado una propuesta en el espacio vecinal de  Cuevas de Ayllón.");
			//				message.setText("Hola vecino de Cuevas de Ayllón, algun vecino ha realizado una propuesta con el titulo: "+propuestas.getTitulo()+", te escribimos este mensaje para dejartelo saber, ya sabes que puedes entrar en tu usuario ,comentar la propuesta y votarla si te apetece, un saludo, seguimos haciendo pueblo.");
			//				mailSender.send(message);
			//				}
			logger.info("propuestaRelizada", "La propuesta a sido realizada ncon exito");
			return propuestaService.findBtNombre(titulo);

		}else {
			logger.info("Esta es la propuesta propuestaComprobar :" +propuestaComprobar.getTitulo());
			Propuestas propuestas=new Propuestas();	
			propuestas.setPropuesta("propuesta Existente");
			propuestas.setTitulo("propuesta Existente");
			logger.info("Entramos en metodo propuesta no existente");
			Usuario usuario=(Usuario) sesion.getAttribute("usuario");
			System.out.println("el usuario es:"+usuario.getNombre());
			propuestas.setUsuario(usuario);
			logger.info("propuestaExistente", "Esta propuiesta ya existe");
			model.addAttribute("propuestaExistente", "Esta propuesta ya existe");
			return propuestas;
		}





	}
	/**
	 * Metodo que se utiliza para que los usuarios editen sus propias propuestas
	 * @param idPropuesta
	 * @param titulo
	 * @param propuesta
	 * @param model
	 * @param sesion
	 * @return
	 */
	@PostMapping( value = ("/editarPropuesta"), produces = MediaType.APPLICATION_JSON_VALUE)
	public String editarPropuesta(@RequestParam("idPropuesta")int idPropuesta,@RequestParam("titulo")String titulo,@RequestParam("propuesta")String propuesta,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo editar propuesta");
		Propuestas propuestaComprobar = new Propuestas();

		if(idPropuesta>0) {

			propuestaComprobar=propuestaService.findByIdPropuesta(idPropuesta);

		}

		if(propuestaComprobar!=null) {

			Propuestas propuestas=new Propuestas();	
			propuestas.setIdPropuesta(propuestaComprobar.getIdPropuesta());
			propuestas.setFecha(propuestaComprobar.getFecha());
			propuestas.setPropuesta(propuesta);
			propuestas.setTitulo(titulo);			
			propuestas.setUsuario(propuestaComprobar.getUsuario());
			propuestaService.save(propuestas);

			logger.info("propuestaRelizada", "La propuesta a sido editada con exito");
			List<Propuestas>misPropuestas=propuestaService.findById_Usuario(propuestaComprobar.getUsuario().getIdUsuario());

			misPropuestas.forEach(f->logger.info("Estas son mis propuestas: "+f.getIdPropuesta()));

			model.addAttribute("misPropuestas", misPropuestas);
			model.addAttribute("propuestaEditada", "La propuesta a sido editada con exito");
			return "misPropuestas";

		}


		return "misPropuestas";

	}
	@GetMapping( value = ("/propuesta"), produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Propuestas> todasPropuesta(Model model,HttpSession sesion) {

		List<Propuestas>todas=propuestaService.findAll();

		todas=todas.stream().sorted(Comparator.comparing(Propuestas::getIdPropuesta)
				.reversed()).collect(Collectors.toList());

		return todas;

	}



	@GetMapping("/comentarios")
	public String comentarios(@RequestParam("idPropuesta") int idpropuesta,Model model,HttpSession sesion){

		logger.info("Entramos en metodo comentarios esta es la idpropuesta"+idpropuesta);

		List<Comentarios> comentarios=comentarioService.findAllByIdPropuesta(idpropuesta);
		List<ComentariosDto>comentarioDtoList=new ArrayList<>();
		Usuario usuario=(Usuario) sesion.getAttribute("usuario");

		for(int i =0;i<comentarios.size();i++) {

			ComentariosDto comentarioDto=Utilidades.comentariosTocomentariosDto(comentarios.get(i));

			comentarioDtoList.add(comentarioDto);

			if(comentarios.get(i).getUsuario().getIdUsuario()!=usuario.getIdUsuario()) {


				comentarioDtoList.get(i).setEditable(null);



			}else {
				comentarioDtoList.get(i).setEditable("si");

			}

		}

		Propuestas propuesta=propuestaService.findByIdPropuesta(idpropuesta);

		logger.info("Estas son la propuestas que recogemos en metodo comentarios:["+propuesta.getPropuesta()+" "+propuesta.getIdPropuesta()+"]");

		model.addAttribute("comentarios", comentarioDtoList);

		comentarios.forEach(c ->logger.info("Estos son los usuarios: "+c.getUsuario().getNombre()));

		model.addAttribute("propuestas", propuesta);

		sesion.setAttribute("usuario", usuario);

		sesion.setAttribute("propuestas", propuesta);

		return "comentarios";

	}
	@GetMapping("/editarComentario")
	public String editarComentarios(@RequestParam("idComentario") int idcomentario,@RequestParam("comentario") String coment,Model model,HttpSession sesion){

		logger.info("Entramos en metodo editarcomentarios esta es la idcomentario"+idcomentario);

		Objetos objetos=new Objetos();
		Comentarios comentario=comentarioService.findByid(idcomentario);

		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		Propuestas propuesta=propuestaService.findBtNombre(comentario.getPropuesta().getTitulo());	;


		if(comentario!=null) {
			logger.info("Entramos en el metodo salvarcomentario la lista esta a 0 "+comentario.getComentario());
			comentario.setUsuario(comentario.getUsuario());
			comentario.setEditable("si");
			comentario.setPropuesta(comentario.getPropuesta());
			comentario.setComentario(coment);
			comentarioService.save(comentario);
		}
		List<Comentarios> comentarios=comentarioService.findAllByIdPropuesta(propuesta.getIdPropuesta());
		List<ComentariosDto>comentarioDtoList=new ArrayList<>();
		for(int i =0;i<comentarios.size();i++) {

			ComentariosDto comentarioDto=Utilidades.comentariosTocomentariosDto(comentarios.get(i));

			comentarioDtoList.add(comentarioDto);

			if(comentarios.get(i).getUsuario().getIdUsuario()!=usuario.getIdUsuario()) {


				comentarioDtoList.get(i).setEditable(null);



			}else {
				comentarioDtoList.get(i).setEditable("si");

			}

		}
		model.addAttribute("propuestas", propuesta);
		model.addAttribute("usuario", usuario);
		model.addAttribute("comentarioEditable", comentarioDtoList);
		model.addAttribute("comentarios", comentarioDtoList);


		return "comentarios";

	}
	/**
	 * Metodo que utilizamos para borrar los comentarios
	 * @param idcomentario
	 * @param idpropuesta
	 * @param model
	 * @param sesion
	 * @return
	 */
	@PostMapping("/borrarComentario")
	public String borrarComentarios(@RequestParam("idComentario") int idcomentario,@RequestParam("idPropuesta") int idpropuesta,Model model,HttpSession sesion){

		logger.info("Entramos en metodo borrarcomentarios esta es la idcomentario"+idcomentario);
		Comentarios comentario=new Comentarios();
		Propuestas propuesta =new Propuestas();

		if(idcomentario>0) {
			comentario=comentarioService.findByid(idcomentario);
		}

		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		if(comentario!=null) {
			propuesta=propuestaService.findBtNombre(comentario.getPropuesta().getTitulo());	
		}else {
			propuesta=(Propuestas) sesion.getAttribute("propuestas");
		}


		if(comentario!=null) {
			logger.info("Entramos en el metodo borrarcomentario la lista esta a 0 "+comentario.getComentario());

			comentarioService.deleteById(comentario, idcomentario);
		}
		List<Comentarios> comentarios=comentarioService.findAllByIdPropuesta(propuesta.getIdPropuesta());

		for(int i =0;i<comentarios.size();i++) {

			if(comentarios.get(i).getUsuario().getIdUsuario()!=usuario.getIdUsuario()) {


				comentarios.get(i).setEditable(null);
			}

		}
		model.addAttribute("propuestas", propuesta);
		model.addAttribute("usuario", usuario);
		model.addAttribute("comentarioEditable", comentario);
		model.addAttribute("comentarios", comentarios);


		return "comentarios";

	}
	/**
	 * Metodo que utilizamos para salvar los comentarios
	 * @param comentario
	 * @param model
	 * @param sesion
	 * @return
	 */
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
			comentarios.setEditable("si");
			comentarios.setPropuesta(propuesta);
			comentarios.setComentario(comentario);
			comentarioService.save(comentarios);
		}
		lista.add(comentarios);
		for(int i =0;i<lista.size();i++) {

			if(lista.get(i).getUsuario().getIdUsuario()!=usuario.getIdUsuario()) {


				lista.get(i).setEditable(null);
			}

		}
		objetos.setComentarios(lista);
		objetos.setUsuario(usuario);
		objetos.setPropuestas(propuesta);

		logger.info("Este es el usuario= "+objetos.getUsuario().getNombre());
		logger.info("Esta es la propuesta= "+objetos.getPropuestas().getTitulo());
		logger.info("Esta es el comentario= "+objetos.getPropuestas().getIdPropuesta());


		return objetos;

	}
	/**
	 * Este metodo devuelve los comentarios 
	 * @param sesion
	 * @return
	 */
	@GetMapping( value = ("todosComentarios"),produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody  List<Comentarios> todosComentario(HttpSession sesion) {
		//		List<String> comentariosUsuarios=new ArrayList<>();
		List<Comentarios>comentarios=new ArrayList<>();
		Propuestas propuesta=new Propuestas();
		logger.info("Entramos en metodo todosComentario");

		if(sesion.getAttribute("propuestas")!=null) {

			propuesta=(Propuestas) sesion.getAttribute("propuestas");


			comentarios=comentarioService.findAllByIdPropuesta(propuesta.getIdPropuesta());

			for(Comentarios e:comentarios) {
				//				comentariosUsuarios.add(e.getUsuario().getFoto().toString());
				//				comentariosUsuarios.add(e.getUsuario().getNombre().toString());		
				//				comentariosUsuarios.add(e.getComentario().toString());

				logger.info("Entramos en metodo todosComentario foto: "+e.getUsuario().getFoto().toString());

				logger.info("Entramos en metodo todosComentario nombre usuario: "+e.getUsuario().getNombre());

				logger.info("Entramos en metodo todosComentario comentario: "+e.getComentario());


			}
			return comentarios;
		}

		return comentarios;

	}

//	@ModelAttribute("comentarios")
//	public List<Comentarios> listaPaises(HttpSession sesion){
//
//		List<Comentarios> lista=(List<Comentarios>) sesion.getAttribute("comentarios");
//
//		return lista;
//	}
	/**
	 * Metodo que añade un punto a la propuesta que recupera por sesion
	 * @param mas
	 * @param model
	 * @param sesion
	 * @return
	 */
	@GetMapping("/puntuacionMas")
	public String puntuacionMasUno(@RequestParam(required=false)String mas,@RequestParam("titulo")String titulo,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo /puntuacionMas con mas="+ mas);

		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		Propuestas propuesta=propuestaService.findBtNombre(titulo);
		List<Comentarios> listaComentario=comentarioService.findAllByIdPropuesta(propuesta.getIdPropuesta());
		Puntuacion  puntuacion = new Puntuacion ();
		Puntuacion puntuacioncheck=puntuacionservice.puntuacionDePropuesta(usuario.getNombre(), titulo);
		int contador=0 ;
		if(puntuacioncheck==null) {
			logger.info("puntuacioncheck= null "+ mas);
			contador =1;
			puntuacion.setId_puntuacion(0);
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
		for(int i =0;i<listaComentario.size();i++) {

			if(listaComentario.get(i).getUsuario().getIdUsuario()!=usuario.getIdUsuario()) {


				listaComentario.get(i).setEditable(null);
			}

		}
		model.addAttribute("propuestas", propuesta);
		model.addAttribute("comentarios", listaComentario);
		model.addAttribute("usuario", usuario);
		model.addAttribute("puntuacion",totalPuntos);

		return "comentarios";



	}
	/**
	 * Metodo que resta un punto a la propuesta que recupera por sesion
	 * @param menos
	 * @param model
	 * @param sesion
	 * @return
	 */
	@GetMapping("/puntuacionMenos")
	public String puntuacionMenosUno(@RequestParam(required=false)String menos,@RequestParam("titulo")String titulo,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo /puntuacionMas con mas="+ menos);

		Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		Propuestas propuesta=propuestaService.findBtNombre(titulo);
		List<Comentarios> listaComentario=comentarioService.findAllByIdPropuesta(propuesta.getIdPropuesta());
		Puntuacion  puntuacion = new Puntuacion ();
		Puntuacion puntuacioncheck=puntuacionservice.puntuacionDePropuesta(usuario.getNombre(), titulo);
		int contador=0 ;
		if(puntuacioncheck==null) {
			logger.info("puntuacioncheck= null "+ menos);
			contador =0;
			puntuacion.setId_puntuacion(0);
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
		for(int i =0;i<listaComentario.size();i++) {

			if(listaComentario.get(i).getUsuario().getIdUsuario()!=usuario.getIdUsuario()) {


				listaComentario.get(i).setEditable(null);
			}

		}
		model.addAttribute("propuestas", propuesta);
		model.addAttribute("comentarios", listaComentario);
		model.addAttribute("usuario", usuario);
		//model.addAttribute("puntuacion",totalPuntos);
		return "comentarios";
	}
	/**
	 * Este emtodo devuelve la suma de los puntos que tiene una propuesta que recibimos por sesion
	 * @param sesion
	 * @return
	 */
	@GetMapping(value = "puntuacionTotal",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int  listaPuntosTotales(HttpSession sesion){
		logger.info("Entramos en metodo /puntuacionTotal ");
		int totalPuntos=0;
		if(sesion.getAttribute("propuestas")!=null) {
			Propuestas propuesta=(Propuestas) sesion.getAttribute("propuestas");

			List<Puntuacion> lista= puntuacionservice.listaDePuntos(propuesta.getTitulo());

			totalPuntos=lista.stream().mapToInt(d->d.getPuntuacion()).sum();
			sesion.setAttribute("totalPuntos", totalPuntos);
			return totalPuntos;
		}

		return totalPuntos;
	}
	/**
	 * Este metodo devuelve todas la propuesta, se usa para los administradores
	 * @param model
	 * @return
	 */
	@GetMapping("/listaPropuestas")
	public String todasLasPropuestas(Model model){
		logger.info("Entramos en metodo /listaPropuestas");
		List<Propuestas>todas=propuestaService.findAll();

		todas=todas.stream().sorted(Comparator.comparing(Propuestas::getIdPropuesta)
				.reversed()).collect(Collectors.toList());
		model.addAttribute("listaPropuestas", todas);
		return "listaPropuestas";




	}
	/**
	 * Metodo que se utiliza pa borrar una propuesta pr los administradores
	 * @param idPropuesta
	 * @param model
	 * @return
	 */
	@PostMapping("/borrarPropuesta")
	public String borrarPropuesta(@RequestParam("idPropuesta")int idPropuesta,Model model) {
		logger.info("Entramos en metodo /borrarPropuesta");
		if(idPropuesta>0) {
			propuestaService.deleteById(idPropuesta);


		}
		List<Propuestas>todas=propuestaService.findAll();
		model.addAttribute("listaPropuestas", todas);

		return "listaPropuestas";

	}
	/**
	 * Este metodo guarda el total de una propuesta que recupera de la sesion
	 * @param total
	 * @param sesion
	 * @return
	 */
	@GetMapping(value ="/guardarTotal",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int guardarTotalPuntos(@RequestParam("total")int total,HttpSession sesion) {
		logger.info("Entramos en metodo /guardarTotal");
		if(sesion.getAttribute("propuestas")!=null) {
			Propuestas propuestaT=(Propuestas) sesion.getAttribute("propuestas");

			puntuacionTotalservice.salvarPuntuacion(propuestaT.getTitulo(), total);

		}
		return total;

	}

	@GetMapping("/votacionPropuestas")
	public String resultadoVotaciones(Model model) {

		List<PuntuacionTotal>resultadoToatles=puntuacionTotalservice.todasLasPuntuaciones();

		model.addAttribute("listaPuntuaciones", resultadoToatles);

		return"puntuacionPropuestas";

	}
	@GetMapping("/misPropuestas")
	public String misPropuestas(@RequestParam("idUsuario")int idUsuario,Model model) {

		List<Propuestas>misPropuestas=propuestaService.findById_Usuario(idUsuario);

		model.addAttribute("misPropuestas", misPropuestas);

		return"misPropuestas";

	}
	@PostMapping("/borrarMiPropuesta")
	public String borrarMiPropuesta(@RequestParam("idPropuesta")int idPropuesta,@RequestParam("idUsuario")int idUsuario,Model model) {
		logger.info("Entramos en metodo /borrarPropuesta");
		if(idPropuesta>0) {
			propuestaService.deleteById(idPropuesta);
			model.addAttribute("propuestaBorrada", "La propuesta se ha borrado con exito");

		}
		List<Propuestas>misPropuestas=propuestaService.findById_Usuario(idUsuario);

		model.addAttribute("misPropuestas", misPropuestas);
		

		return "misPropuestas";

	}
	/**
	 * Este metodo borra el total de una puntuacion que recupera por parametro
	 * @param idPuntuacion
	 * @param model
	 * @return
	 */
	@PostMapping("/borrarPuntuacion")
	public String borrarPuntuacion(@RequestParam("idPuntuacion")int idPuntuacion,Model model) {
		logger.info("Entramos en metodo /borrarPropuesta");
		if(idPuntuacion>0) {
			puntuacionTotalservice.borrarPuntuacion(idPuntuacion);


		}
		List<PuntuacionTotal>resultadoToatles=puntuacionTotalservice.todasLasPuntuaciones();

		model.addAttribute("listaPuntuaciones", resultadoToatles);

		return"puntuacionPropuestas";



	}

}
