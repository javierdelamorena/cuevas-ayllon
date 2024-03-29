package com.cuevasdeayllon.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.math3.exception.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cuevasdeayllon.entity.Mercadillo;
import com.cuevasdeayllon.entity.Usuario;
import com.cuevasdeayllon.paginator.PageRender;
import com.cuevasdeayllon.service.MercadilloService;
import com.cuevasdeayllon.service.UsuarioService;

@Controller
public class MercadilloController {

	private static final Logger logger = LoggerFactory.getLogger(MercadilloController.class);

	@Autowired
	private UsuarioService service;

	@Autowired
	private MercadilloService mercadilloservice;

	// static final String ROOT_PATH = "C://TEMP//uploadsMercadillo";
	static final String ROOT_PATH = "/uploadsMercadillo";

	@PostMapping(path = "/mercadilloServicio")
	public String registrarMercadillo(@Valid Mercadillo mercadillo, BindingResult result,
			@RequestParam("file1") MultipartFile foto1, @RequestParam("file2") MultipartFile foto2,
			@RequestParam("file3") MultipartFile foto3, Model model, HttpSession sesion) {
		logger.info("Entramos en metodo mercadillo");
		logger.info("El nombre de usuario que recogemos es: " + mercadillo.getNombre());
		if (mercadillo.getId_usuario() == 0) {
			return "login";
		}
		if (result.hasErrors()) {
			logger.info("Entramos en metodo index/mercadillo el idusuario es: " + mercadillo.getId_usuario());
			List<String> tipoServicio = Arrays.asList("Compra", "Venta", "Servicios", "Alquiler");
			Usuario usuario = service.usuarioPorId(mercadillo.getId_usuario());

			mercadillo.setId_usuario(usuario.getIdUsuario());
			model.addAttribute("tipo_servicio", tipoServicio);
			model.addAttribute("nombre", "Nombre del anunciante");
			model.addAttribute("categoria", "Categoría ");
			model.addAttribute("servicio", "Tipo de servicio");
			model.addAttribute("foto1", "Foto 1");
			model.addAttribute("foto2", "Foto 2");
			model.addAttribute("foto3", "Foto 3");
			model.addAttribute("precio", "Precio");
			model.addAttribute("texto", "Escribe tu oferta");
			model.addAttribute("nombre_servicio", "Tipo de servicio o articulo");

			model.addAttribute("telefono", "Telefono del anunciante");

			model.addAttribute("usuario", usuario);
			model.addAttribute("mercadillo", mercadillo);

			return "/mercadillo";

		}

		try {

			if (mercadillo != null) {
				logger.info("El usuario es distinto de null");

			}

			if (!foto1.isEmpty() && mercadillo != null) {

				int oraLen = foto1.getOriginalFilename().length();
				logger.info("El nombre de la foto es: " + foto1.getOriginalFilename());

				for (int i = 0; i < oraLen; i++) {
					if (foto1.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("espaciosBlancos",
								"El nombre de la foto1 no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						List<String> tipoServicio = Arrays.asList("Compra", "Venta", "Servicios", "Alquiler");
						model.addAttribute("tipo_servicio", tipoServicio);
						model.addAttribute("nombre", "Nombre del anunciante");
						model.addAttribute("categoria", "Categoría ");
						model.addAttribute("servicio", "Tipo de servicio");
						model.addAttribute("foto1", "Foto 1");
						model.addAttribute("foto2", "Foto 2");
						model.addAttribute("foto3", "Foto 3");
						model.addAttribute("precio", "Precio");
						model.addAttribute("texto", "Escribe tu oferta");
						model.addAttribute("nombre_servicio", "Tipo de servicio o articulo");

						model.addAttribute("telefono", "Telefono del anunciante");

						return "/mercadillo";
					}
				}

			}
			if (!foto2.isEmpty() && mercadillo != null) {
				int oraLen = foto1.getOriginalFilename().length();
				logger.info("El nombre de la foto1 es: " + foto1.getOriginalFilename());
				for (int i = 0; i < oraLen; i++) {
					if (foto1.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("espaciosBlancos",
								"El nombre de la foto2 no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						List<String> tipoServicio = Arrays.asList("Compra", "Venta", "Servicios", "Alquiler");
						model.addAttribute("tipo_servicio", tipoServicio);
						model.addAttribute("nombre", "Nombre del anunciante");
						model.addAttribute("categoria", "Categoría ");
						model.addAttribute("servicio", "Tipo de servicio");
						model.addAttribute("foto1", "Foto 1");
						model.addAttribute("foto2", "Foto 2");
						model.addAttribute("foto3", "Foto 3");
						model.addAttribute("precio", "Precio");
						model.addAttribute("texto", "Escribe tu oferta");
						model.addAttribute("nombre_servicio", "Tipo de servicio o articulo");

						model.addAttribute("telefono", "Telefono del anunciante");
						return "/mercadillo";
					}
				}

			}
			if (!foto3.isEmpty() && mercadillo != null) {

				int oraLen = foto3.getOriginalFilename().length();
				logger.info("El nombre de la foto es: " + foto3.getOriginalFilename());

				for (int i = 0; i < oraLen; i++) {
					if (foto3.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("espaciosBlancos",
								"El nombre de la foto3 no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						List<String> tipoServicio = Arrays.asList("Compra", "Venta", "Servicios", "Alquiler");
						model.addAttribute("tipo_servicio", tipoServicio);
						model.addAttribute("nombre", "Nombre del anunciante");
						model.addAttribute("categoria", "Categoría ");
						model.addAttribute("servicio", "Tipo de servicio");
						model.addAttribute("foto1", "Foto 1");
						model.addAttribute("foto2", "Foto 2");
						model.addAttribute("foto3", "Foto 3");
						model.addAttribute("precio", "Precio");
						model.addAttribute("texto", "Escribe tu oferta");
						model.addAttribute("nombre_servicio", "Tipo de servicio o articulo");

						model.addAttribute("telefono", "Telefono del anunciante");
						return "/mercadillo";
					}
				}

			}
			mercadilloservice.insertarMercadillo(mercadillo, foto1, foto2, foto3);

			logger.info("Entramos en metodo index/mercadillo" + mercadillo.getId());
			List<String> tipoServicio = Arrays.asList("Compra", "Venta", "Servicios", "Alquier");
			Usuario usuario = service.usuarioPorId(mercadillo.getId());
			Mercadillo mercado = new Mercadillo();
			mercado.setId_usuario(mercadillo.getId());
			model.addAttribute("tipo_servicio", tipoServicio);
			model.addAttribute("nombre", "Nombre del anunciante");
			model.addAttribute("categoria", "Categoría ");
			model.addAttribute("servicio", "Tipo de servicio");
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

		} catch (javax.validation.ConstraintViolationException e) {

			return "/mercadillo";

		}

		return "/mercadillo";

	}

	@PostMapping(path = "/editarMimercadillo")
	public String editarMimercadillo(@Valid Mercadillo mercadillo, BindingResult result,
			@RequestParam("file1") MultipartFile foto1, @RequestParam("file2") MultipartFile foto2,
			@RequestParam("file3") MultipartFile foto3, Model model, HttpSession sesion) throws Exception {
		Mercadillo mercado = mercadilloservice.findById(mercadillo.getId());
		try {
			if (mercado == null) {
				throw new Exception("El id que recibimos no corresponde con ningun mercado");
			}

			if (mercado.getId_usuario() == 0) {
				return "login";
			}
			logger.info("Entramos en metodo editar mercadillo el testo es: " + mercadillo.getTexto());
			logger.info("El articulo que recogemos es: " + mercado.getNombre());

			if (!foto1.isEmpty() && mercado != null) {

				int oraLen = foto1.getOriginalFilename().length();
				logger.info("El nombre de la foto es: " + foto1.getOriginalFilename());

				for (int i = 0; i < oraLen; i++) {
					if (foto1.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("mercadilloBorrado",
								"El nombre de la foto no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						return "/miMercadillo";
					}
				}

			}
			if (!foto2.isEmpty() && mercado != null) {
				int oraLen = foto2.getOriginalFilename().length();
				logger.info("El nombre de la foto2 es: " + foto2.getOriginalFilename());
				for (int i = 0; i < oraLen; i++) {
					if (foto2.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("mercadilloBorrado",
								"El nombre de la foto no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						return "/miMercadillo";
					}
				}

			}
			if (!foto3.isEmpty() && mercado != null) {

				int oraLen = foto3.getOriginalFilename().length();
				logger.info("El nombre de la foto es: " + foto3.getOriginalFilename());

				for (int i = 0; i < oraLen; i++) {
					if (foto3.getOriginalFilename().charAt(i) == ' ') {
						model.addAttribute("mercadilloBorrado",
								"El nombre de la foto no puede tener espacios en blanco.Cambie el nombre de la foto y añadala de nuevo, gracias.");
						return "/miMercadillo";
					}
				}

			}

			if (result.hasErrors()) {
				logger.info("Entramos en metodo index/mercado el idusuario es: " + mercadillo.getId_usuario());
				List<String> tipoServicio = Arrays.asList("Compra", "Venta", "Servicios", "Alquiler");
				Usuario usuario = service.usuarioPorId(mercado.getId_usuario());
				List<Mercadillo> todos = mercadilloservice.todosLosMercadillosiIdUsuario(mercado.getId_usuario());
				mercadillo.setId_usuario(usuario.getIdUsuario());
				model.addAttribute("tipo_servicio", tipoServicio);
				model.addAttribute("nombre", "Nombre del anunciante");
				model.addAttribute("categoria", "Categoría ");
				model.addAttribute("servicio", "Tipo de servicio");
				model.addAttribute("foto1", "Foto 1");
				model.addAttribute("foto2", "Foto 2");
				model.addAttribute("foto3", "Foto 3");
				model.addAttribute("precio", "Precio");
				model.addAttribute("texto", "Escribe tu oferta");
				model.addAttribute("nombre_servicio", "Tipo de servicio o articulo");
				model.addAttribute("mercadilloBorrado",
						"Algo fue mal, el Nombre, el Nombre del Servicio, la Categoría, el Texto y el Teléfono hay que rellenarlos");

				model.addAttribute("telefono", "Telefono del anunciante");

				model.addAttribute("usuario", usuario);
				model.addAttribute("miMercadillo", todos);
				model.addAttribute("mercadilloBorrado",
						"Ha habido un error en la edicion del mercadillo añadala de nuevo, gracias.");
				logger.info("Entramos en metodo index/mercadillo Ha habido un error en la edicion del mercadillo:");
				return "/miMercadillo";

			}

			if (mercado != null) {
				logger.info("El usuario es distinto de null");

			}

			mercadilloservice.actualizarMercadillo(mercadillo, foto1, foto2, foto3);

		} catch (javax.validation.ConstraintViolationException e) {
			e.printStackTrace();

			return "/miMercadillo";

		}
		List<Mercadillo> todos = mercadilloservice.todosLosMercadillosiIdUsuario(mercado.getId_usuario());

		if (todos.size() > 0) {
			model.addAttribute("miMercadillo", todos);
		} else {
			model.addAttribute("miMercadilloVacio", "No hay ofertas que mostrar");
		}
		model.addAttribute("miMercadilloVacio", "Tu oferta se editó con exito");
		return "/miMercadillo";

	}

//	@GetMapping(path = "/mercadilloTodos")
//	public String todosMercadillo(Model model) {
//
//		logger.info("Entramos en metodo mercadilloTodos");
//		List<Mercadillo> todos = mercadilloservice.todosLosMercadillos();
//		if (todos.size() > 0) {
//			todos = todos.stream().sorted(Comparator.comparing(Mercadillo::getId).reversed())
//					.collect(Collectors.toList());
//			model.addAttribute("mercadillo", todos);
//		}
//
//		return "mercadilloExterior";
//
//	}

	@GetMapping(path = "/mercadilloTodosAdministrador")
	public String todosMercadilloAdministrador(Model model) {

		logger.info("Entramos en metodo mercadilloTodosAdministrador");
		List<Mercadillo> todos = mercadilloservice.todosLosMercadillos();

		model.addAttribute("mercadillo", todos);
		return "/listaMercadillos";

	}

	@GetMapping(path = "/miMercadillo")
	public String miMercadillo(@RequestParam("idUsuario") int id_usuario, Model model) {

		logger.info("Entramos en metodo mercadilloTodosAdministrador");
		List<Mercadillo> todos = mercadilloservice.todosLosMercadillosiIdUsuario(id_usuario);

		List<String> tipoServicio = Arrays.asList("Compra", "Venta", "Servicios", "Alquiler");
		model.addAttribute("tipo_servicio", tipoServicio);

		if (todos.size() > 0) {
			model.addAttribute("miMercadillo", todos);
		} else {
			model.addAttribute("miMercadilloVacio", "No hay ofertas que mostrar");
		}

		return "/miMercadillo";

	}

	@PostMapping(path = "/borrarMimercadillo")
	public String borrarMimercadillo(@RequestParam("id") int id, @RequestParam("idUsuario") int idUsuario,
			Model model) {
		Mercadillo mercadilloComprobar = mercadilloservice.findById(id);
		logger.info("Entramos en metodo borrarMimercadillo");
		if (id > 0 && mercadilloComprobar != null) {
			logger.info("Entramos en metodo borrarMimercadillo el id es: " + id);
			mercadilloservice.borrarMercadillo(id);

			model.addAttribute("mercadilloBorrado", "Tu oferta se ha borrado con exito");
			List<Mercadillo> todos = mercadilloservice.todosLosMercadillosiIdUsuario(idUsuario);
			if (todos.size() > 0) {
				model.addAttribute("miMercadillo", todos);
			} else {
				model.addAttribute("miMercadilloVacio", "No hay ofertas que mostrar");
			}
		}
		return "/miMercadillo";

	}

	@PostMapping(path = "/borrarMercadillo")
	public String borrarMimercadillo(@RequestParam("id") int id, Model model) {

		logger.info("Entramos en metodo mercadilloTodosAdministrador");
		Mercadillo mercadilloComprobar = mercadilloservice.findById(id);
		if (id > 0 && mercadilloComprobar != null) {
			mercadilloservice.borrarMercadillo(id);

			model.addAttribute("mercadilloBorrado", "La oferta se ha borrado con exito");
			List<Mercadillo> todos = mercadilloservice.todosLosMercadillos();
			if (todos.size() > 0) {
				model.addAttribute("mercadillo", todos);
			}
		}
		return "/listaMercadillos";

	}

	@GetMapping(path = "/tipoServicio")
	public @ResponseBody List<Mercadillo> mercadilloServicioPageTipoServicio(@RequestParam("page") String page,
			@RequestParam("tipoServicio") String tipoServicio, Model model) {
		logger.info("Entramos en metodo /tipoServicio las paginas son:" + page);
		List<Mercadillo> objetos = null;
		Pageable pagaRequest = PageRequest.of(Integer.parseInt(page), 1,Sort.by("id").descending());

		Page<Mercadillo> objetosPage = mercadilloservice.findPaginaByTipo_servicio(pagaRequest, tipoServicio);

		PageRender<Mercadillo> pageRender = new PageRender<>("/tipoServicio", objetosPage);

		pageRender.getPaginas().forEach(p -> logger.info("estas son las imagenes" + p.getNumero()));
		objetosPage.forEach(p -> logger.info("estas son las imagenes" + p.getNombre()));
		objetos = objetosPage.getContent();
		objetos.forEach(p -> logger.info("este es el tipo" + p.getTipo_servicio()
				+ " este es el nombre en filtrar por tipo: " + p.getNombre_servicio()));

		return objetos;

	}

	@GetMapping(path = "/nombreServicio")
	public @ResponseBody List<Mercadillo> mercadilloServicioPageNombreServicio(@RequestParam("page") String page,
			@RequestParam("nombreServicio") String nombreServicio, Model model) {
		logger.info("Entramos en metodo /nombreServicio las paginas son:" + page + " el nombre del servicio: "
				+ nombreServicio);
		List<Mercadillo> objetos = null;
		Pageable pagaRequest = PageRequest.of(Integer.parseInt(page), 1,Sort.by("id").descending());

		Page<Mercadillo> objetosPage = mercadilloservice.findPaginaByNombre_servicio(pagaRequest,
				nombreServicio.toUpperCase());

		PageRender<Mercadillo> pageRender = new PageRender<>("/nombreServicio", objetosPage);

		pageRender.getPaginas().forEach(p -> logger.info("estas son las imagenes" + p.getNumero()));

		objetos = objetosPage.getContent();
		objetos.forEach(p -> logger.info("este es el nombre en filtrar por nombre" + p.getNombre_servicio()));

		return objetos;

	}

	@GetMapping(path = "/mercadilloPaginasPreciosTipoServicio")
	public @ResponseBody List<Mercadillo> mercadilloPaginasPreciosTipoServicio(@RequestParam("page") String page,
			@RequestParam("tipoServicio") String tipoServicio,
			@RequestParam(value = "precioMin", required = false) String precioMinimo,
			@RequestParam(value = "precioMax", required = false) String precioMaximo, Model model) {
//		@RequestParam(value = "precioMin", required = false) int precioMin,
//		@RequestParam(value = "precioMax", required = false) int precioMax, Model model) {
		if (tipoServicio.equals("Buscar por tipo......")) {
			tipoServicio = null;
		}
		logger.info("Entramos en metodo /mercadilloPaginasPreciosTipoServicio tipoServicio:" + tipoServicio);
		logger.info("Entramos en metodo /mercadilloPaginasPreciosTipoServicio precioMinimo:" + precioMinimo);
		logger.info("Entramos en metodo /mercadilloPaginasPreciosTipoServicio precioMaximo:" + precioMaximo);
		Integer precioMin = 0;
		Integer precioMax = 0;
		try {
			if (precioMinimo.equals("")) {
				precioMinimo = "0";
			}
			if (precioMaximo.equals("")) {
				precioMaximo = "0";
			}
			precioMin = Integer.parseInt(precioMinimo);
			precioMax = Integer.parseInt(precioMaximo);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		logger.info("Entramos en metodo mercadilloPaginasPreciosTipoServicio la page vale " + page);
		Pageable pagaRequest = PageRequest.of(Integer.parseInt(page), 1,Sort.by("id").descending());

		Page<Mercadillo> todos = null;
//		PageRender<Mercadillo> pageRender = new PageRender<>("/mercadilloPaginasPreciosTipoServicio", todos);
//		pageRender.getPaginas().forEach(p -> logger.info("estas son las imagenes" + p.getNumero()));
		try {
			if (precioMin > 0 && precioMax == 0 && tipoServicio != null) {
				todos = mercadilloservice.findPaginasByTipoServicioPrecioMax(pagaRequest, tipoServicio, precioMin);

			} else if (precioMin == 0 && precioMax > 0 && tipoServicio != null) {
				todos = mercadilloservice.findPaginasByTipoServicioPrecioMax(pagaRequest, tipoServicio, precioMax);

			} else if (precioMin > 0 && precioMax > 0 && tipoServicio != null) {
				todos = mercadilloservice.findPaginasByTipoServicioPrecioMaxMin(pagaRequest, tipoServicio, precioMin,
						precioMax);
			} else if (precioMin != 0 && precioMax != 0 && tipoServicio != null) {
				todos = mercadilloservice.findPaginasByTipoServicioPrecioMaxMin(pagaRequest, tipoServicio, precioMin,
						precioMax);
			} else if (precioMin == 0 && precioMax == 0 && tipoServicio != null) {
				todos = mercadilloservice.findPaginasByTipoServicioPrecioMaxMin(pagaRequest, tipoServicio, precioMin,
						precioMax);
			}

			else if (precioMin > 0 && precioMax == 0 && tipoServicio == null) {
				todos = mercadilloservice.findByMax(pagaRequest, precioMin);

			} else if (precioMin == 0 && precioMax > 0 && tipoServicio == null) {
				todos = mercadilloservice.findByMax(pagaRequest, precioMax);

			} else if (precioMin > 0 && precioMax > 0 && tipoServicio == null) {
				todos = mercadilloservice.findByMaxMin(pagaRequest, precioMin, precioMax);
				
			} else if (precioMin != 0 && precioMax != 0 && tipoServicio == null) {
				todos = mercadilloservice.findByMaxMin(pagaRequest, precioMin, precioMax);
				
			} else if (precioMin == 0 && precioMax == 0 && tipoServicio == null) {
				todos = mercadilloservice.findByMaxMin(pagaRequest, precioMin, precioMax);
			} else {
				todos = null;
			}
		} catch (NullArgumentException e) {
			logger.info(e.getMessage());
		}
		List<Mercadillo> objetos = todos.getContent();

		objetos.forEach(p -> logger.info("este es el nombre en filtrar por precio" + p.getNombre_servicio()));

		return objetos;

	}

	@GetMapping(value = "/mercadilloTodos")
	public @ResponseBody List<Mercadillo> parametrosTabla(@RequestParam("page") String page, Model model,
			RedirectAttributes flash, Locale locale, HttpSession sesion) {

		logger.info("Entramos en METODO  mercadilloTodos esta es la pagina" + page);

		Pageable pagaRequest = PageRequest.of(Integer.parseInt(page), 1,Sort.by("id").descending());

		Page<Mercadillo> objetosPage = mercadilloservice.todasPaginasMercadillo(pagaRequest);

		PageRender<Mercadillo> pageRender = new PageRender<>("/mercadilloTodos", objetosPage);

		pageRender.getPaginas().forEach(p -> logger.info("estas son las imagenes" + p.getNumero()));
		objetosPage.forEach(p -> logger.info("estas son las imagenes" + p.getNombre()));
		List<Mercadillo> objetos = objetosPage.getContent();
		if (objetos != null) {
			return objetos;
		} else {
			return null;
		}
	}
}
