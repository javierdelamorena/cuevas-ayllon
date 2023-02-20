package com.cuevasdeayllon.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Anuncios;
import com.cuevasdeayllon.entity.Mercadillo;
import com.cuevasdeayllon.entity.Usuario;
import com.cuevasdeayllon.repository.MercadilloRepository;
import com.cuevasdeayllon.service.UsuarioService;

@Controller
public class MercadilloController {

	private static final Logger logger = LoggerFactory.getLogger(PropuestaController.class);

	@Autowired
	private UsuarioService service;

	@Autowired
	private MercadilloRepository mercadilloservice;

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping( path= "/mercadilloServicio")
	public String registrarMercadillo(@Valid Mercadillo mercadillo,BindingResult result,@RequestParam("file1")MultipartFile foto1,@RequestParam("file2")MultipartFile foto2,@RequestParam("file3")MultipartFile foto3,Model model,HttpSession sesion) {
		logger.info("Entramos en metodo mercadillo");
		logger.info("El articulo que recogemos es: "+mercadillo.getNombre());
		if(mercadillo.getId_usuario()==0) {
			return "login";
		}
		if (result.hasErrors()) {
			logger.info("Entramos en metodo index/mercadillo el idusuario es: "+mercadillo.getId_usuario());
			List<String>tipoServicio= Arrays.asList("Compra Venta ","Servicios","Alquiler");
			Usuario usuario=service.usuarioPorId(mercadillo.getId_usuario());

			mercadillo.setId_usuario(usuario.getIdUsuario());
			model.addAttribute("tipo_servicio",tipoServicio);
			model.addAttribute("nombre","Nombre del anunciante");
			model.addAttribute("categoria","Categoría ");
			model.addAttribute("servicio","Tipo de servicio");
			model.addAttribute("foto1", "Foto 1");
			model.addAttribute("foto2", "Foto 2");
			model.addAttribute("foto3", "Foto 3");
			model.addAttribute("precio", "Precio");
			model.addAttribute("texto", "Escribe tu oferta");
			model.addAttribute("nombre_servicio", "Tipo de servicio o articulo");


			model.addAttribute("telefono", "Telefono del anunciante");

			model.addAttribute("usuario", usuario);
			model.addAttribute("mercadillo", mercadillo);

			return "mercadillo";

		}

		try {

			if( mercadillo!=null) {
				logger.info("El usuario es distinto de null");

			}
			//String rootPath="/uploadsMercadillo/";
			String rootPath="C://TEMP//uploadsMercadillo";

			if(!foto1.isEmpty()&&mercadillo!=null) {

				int oraLen = foto1.getOriginalFilename().length();
				logger.info("El nombre de la foto es: "+foto1.getOriginalFilename());

				for (int i = 0; i <  oraLen; i++) {
					if (foto1.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("espaciosBlancos", "El nombre de la foto no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						return "mercadillo";
					}
				}
				try {
					byte[]bytes=foto1.getBytes();
					Path rutaCompleta=Paths.get(rootPath+"//"+foto1.getOriginalFilename());
					logger.info("Esta es la ruta absoluta="+rutaCompleta.toAbsolutePath());
					Files.write(rutaCompleta,bytes);
					mercadillo.setFoto1(foto1.getOriginalFilename());					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if(!foto2.isEmpty()&&mercadillo!=null) {
				int oraLen = foto1.getOriginalFilename().length();
				logger.info("El nombre de la foto1 es: "+foto1.getOriginalFilename());
				for (int i = 0; i <  oraLen; i++) {
					if (foto1.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("espaciosBlancos", "El nombre de la foto no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						return "mercadillo";
					}
				}

				try {
					byte[]bytes=foto2.getBytes();
					Path rutaCompleta=Paths.get(rootPath+"//"+foto2.getOriginalFilename());
					logger.info("Esta es la ruta absoluta="+rutaCompleta.toAbsolutePath());
					Files.write(rutaCompleta,bytes);
					mercadillo.setFoto2(foto2.getOriginalFilename());




				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}if(!foto3.isEmpty()&&mercadillo!=null) {

				int oraLen = foto3.getOriginalFilename().length();
				logger.info("El nombre de la foto es: "+foto3.getOriginalFilename());

				for (int i = 0; i <  oraLen; i++) {
					if (foto3.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("espaciosBlancos", "El nombre de la foto no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						return "mercadillo";
					}
				}

				try {
					byte[]bytes=foto3.getBytes();
					Path rutaCompleta=Paths.get(rootPath+"//"+foto3.getOriginalFilename());
					logger.info("Esta es la ruta absoluta="+rutaCompleta.toAbsolutePath());
					Files.write(rutaCompleta,bytes);
					mercadillo.setFoto3(foto3.getOriginalFilename());




				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			mercadilloservice.insertarMercadillo(mercadillo);

			logger.info("Entramos en metodo index/mercadillo"+mercadillo.getId());
			List<String>tipoServicio= Arrays.asList("Compra Venta ","Servicios","Alquier");
			Usuario usuario=service.usuarioPorId(mercadillo.getId());
			Mercadillo mercado=new Mercadillo();
			mercado.setId_usuario(mercadillo.getId());
			model.addAttribute("tipo_servicio",tipoServicio);
			model.addAttribute("nombre","Nombre del anunciante");
			model.addAttribute("categoria","Categoría ");
			model.addAttribute("servicio","Tipo de servicio");
			model.addAttribute("foto1", "Foto1");
			model.addAttribute("foto2", "Foto2");
			model.addAttribute("foto3", "Foto3");
			model.addAttribute("precio", "Precio");
			model.addAttribute("texto", "Escribe tu oferta");
			model.addAttribute("nombre_servicio", "Tipo de servicio o articulo");
			model.addAttribute("exito", "La oferta se ha añadido con exito");


			model.addAttribute("telefono", "Telefono del anunciante");

			model.addAttribute("usuario", usuario);
			model.addAttribute("mercadillo", mercado);

		}catch(javax.validation.ConstraintViolationException e) {

			return "mercadillo";

		}


		return "mercadillo";

	}

	@PostMapping( path= "/editarMimercadillo")
	public String editarMimercadillo(@Valid  Mercadillo mercadillo,BindingResult result,@RequestParam("file1")MultipartFile foto1,@RequestParam("file2")MultipartFile foto2,@RequestParam("file3")MultipartFile foto3,Model model,HttpSession sesion) {
		Mercadillo mercado=new Mercadillo();
		if(mercadillo.getId_usuario()==0) {
			return "login";
		}
				logger.info("Entramos en metodo mercadillo");
				logger.info("El articulo que recogemos es: "+mercadillo.getNombre());
				if(mercadillo.getId_usuario()==0) {
					return "login";
				}
				if (result.hasErrors()) {
					logger.info("Entramos en metodo index/mercadillo el idusuario es: "+mercadillo.getId_usuario());
					List<String>tipoServicio= Arrays.asList("Compra Venta ","Servicios","Alquiler");
					Usuario usuario=service.usuarioPorId(mercadillo.getId_usuario());
					List<Mercadillo>todos=mercadilloservice.todosLosMercadillosiIdUsuario(mercadillo.getId_usuario());
					mercadillo.setId_usuario(usuario.getIdUsuario());
					model.addAttribute("tipo_servicio",tipoServicio);
					model.addAttribute("nombre","Nombre del anunciante");
					model.addAttribute("categoria","Categoría ");
					model.addAttribute("servicio","Tipo de servicio");
					model.addAttribute("foto1", "Foto 1");
					model.addAttribute("foto2", "Foto 2");
					model.addAttribute("foto3", "Foto 3");
					model.addAttribute("precio", "Precio");
					model.addAttribute("texto", "Escribe tu oferta");
					model.addAttribute("nombre_servicio", "Tipo de servicio o articulo");
					model.addAttribute("mercadilloBorrado", "Algo fue mal, el Nombre, el Nombre del Servicio, la Categoría, el Texto y el Teléfono hay que rellenarlos");
		
					model.addAttribute("telefono", "Telefono del anunciante");
		
					model.addAttribute("usuario", usuario);
					model.addAttribute("miMercadillo", todos);
		
					return "miMercadillo";
		
				}

		try {

			if( mercadillo!=null) {
				logger.info("El usuario es distinto de null");

			}
			String rootPath="/uploadsMercadillo/";
			//String rootPath="C://TEMP//uploadsMercadillo";

			if(!foto1.isEmpty()&&mercadillo!=null) {

				int oraLen = foto1.getOriginalFilename().length();
				logger.info("El nombre de la foto es: "+foto1.getOriginalFilename());

				for (int i = 0; i <  oraLen; i++) {
					if (foto1.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("espaciosBlancos", "El nombre de la foto no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						return "miMercadillo";
					}
				}
				try {
					byte[]bytes=foto1.getBytes();
					Path rutaCompleta=Paths.get(rootPath+"//"+foto1.getOriginalFilename());
					logger.info("Esta es la ruta absoluta="+rutaCompleta.toAbsolutePath());
					Files.write(rutaCompleta,bytes);
					mercado.setFoto1(foto1.getOriginalFilename());					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if(!foto2.isEmpty()&&mercadillo!=null) {
				int oraLen = foto1.getOriginalFilename().length();
				logger.info("El nombre de la foto1 es: "+foto1.getOriginalFilename());
				for (int i = 0; i <  oraLen; i++) {
					if (foto1.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("espaciosBlancos", "El nombre de la foto no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						return "miMercadillo";
					}
				}

				try {
					byte[]bytes=foto2.getBytes();
					Path rutaCompleta=Paths.get(rootPath+"//"+foto2.getOriginalFilename());
					logger.info("Esta es la ruta absoluta="+rutaCompleta.toAbsolutePath());
					Files.write(rutaCompleta,bytes);
					mercado.setFoto2(foto2.getOriginalFilename());




				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}if(!foto3.isEmpty()&&mercadillo!=null) {

				int oraLen = foto3.getOriginalFilename().length();
				logger.info("El nombre de la foto es: "+foto3.getOriginalFilename());

				for (int i = 0; i <  oraLen; i++) {
					if (foto3.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("espaciosBlancos", "El nombre de la foto no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						return "miMercadillo";
					}
				}

				try {
					byte[]bytes=foto3.getBytes();
					Path rutaCompleta=Paths.get(rootPath+"//"+foto3.getOriginalFilename());
					logger.info("Esta es la ruta absoluta="+rutaCompleta.toAbsolutePath());
					Files.write(rutaCompleta,bytes);
					mercado.setFoto3(foto3.getOriginalFilename());




				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			

			
			mercado.setId(mercadillo.getId());
			mercado.setNombre(mercadillo.getNombre());
			mercado.setPrecio(mercadillo.getPrecio());
			mercado.setTelefono(mercadillo.getTelefono());
			mercado.setId_usuario(mercadillo.getId_usuario());
			mercado.setTipo_servicio(mercadillo.getTipo_servicio());
			mercado.setTexto(mercadillo.getTexto());
			mercado.setFoto1(mercadillo.getFoto1());
			mercado.setFoto2(mercadillo.getFoto2());
			mercado.setFoto3(mercadillo.getFoto3());
			mercado.setFecha(new Date());
			mercado.setNombre_servicio(mercadillo.getNombre_servicio());

			mercadilloservice.actualizarMercadillo(mercado);

		}catch(javax.validation.ConstraintViolationException e) {
			e.printStackTrace();

			return "miMercadillo";

		}
		List<Mercadillo>todos=mercadilloservice.todosLosMercadillosiIdUsuario(mercadillo.getId_usuario());
		
		
		model.addAttribute("miMercadillo", todos);

		return "miMercadillo";

	}


	@GetMapping( path= "/mercadilloTodos")
	public String todosMercadillo(Model model) {

		logger.info("Entramos en metodo mercadilloTodos");
		List<Mercadillo>todos=mercadilloservice.todosLosMercadillos();
		todos=todos.stream().sorted(Comparator.comparing(Mercadillo::getId)
				.reversed()).collect(Collectors.toList());

		model.addAttribute("mercadillo", todos);
		return "mercadilloExterior";



	}
	@GetMapping( path= "/mercadilloTodosAdministrador")
	public String todosMercadilloAdministrador(Model model) {

		logger.info("Entramos en metodo mercadilloTodosAdministrador");
		List<Mercadillo>todos=mercadilloservice.todosLosMercadillos();

		model.addAttribute("mercadillo", todos);
		return "listaMercadillos";



	}
	@GetMapping( path= "/miMercadillo")
	public String miMercadillo(@RequestParam("idUsuario")int id_usuario,Model model) {

		logger.info("Entramos en metodo mercadilloTodosAdministrador");
		List<Mercadillo>todos=mercadilloservice.todosLosMercadillosiIdUsuario(id_usuario);

		List<String>tipoServicio= Arrays.asList("Compra Venta ","Servicios","Alquiler");
		model.addAttribute("tipo_servicio",tipoServicio);

		model.addAttribute("miMercadillo", todos);
		return "miMercadillo";



	}
	@PostMapping( path= "/borrarMimercadillo")
	public String borrarMimercadillo(@RequestParam("id")int id,@RequestParam("idUsuario")int idUsuario,Model model) {

		logger.info("Entramos en metodo mercadilloTodosAdministrador");
		if(id>0) {
			mercadilloservice.borrarMercadillo(id);

			model.addAttribute("mercadilloBorrado", "Tu oferta se ha borrado con exito");
			List<Mercadillo>todos=mercadilloservice.todosLosMercadillosiIdUsuario(idUsuario);

			model.addAttribute("miMercadillo", todos);
		}
		return "miMercadillo";



	}
	@PostMapping( path= "/borrarMercadillo")
	public String borrarMimercadillo(@RequestParam("id")int id,Model model) {

		logger.info("Entramos en metodo mercadilloTodosAdministrador");
		if(id>0) {
			mercadilloservice.borrarMercadillo(id);

			model.addAttribute("mercadilloBorrado", "La oferta se ha borrado con exito");
			List<Mercadillo>todos=mercadilloservice.todosLosMercadillos();

			model.addAttribute("mercadillo", todos);
		}
		return "listaMercadillos";



	}



}
